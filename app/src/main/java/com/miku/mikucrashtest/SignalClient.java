package com.miku.mikucrashtest;

import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import timber.log.Timber;

import static com.miku.mikucrashtest.Config.SIGNAL_URL;

public class SignalClient extends WebSocketListener {
    private static final String TAG = "SignalClient";

//  private static final String EXPECTED_FINGERPRINT = "6A:1B:FF:5C:D6:EF:B4:DF:DC:F0:F2:41:50:86:8B:97:15:9D:93:15:31:97:F3:02:2B:9D:36:4D:AB:A8:3D:FA";

    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private SignalHandler callback;
    private OkHttpClient client;
    private WebSocket socket;
    private String toFingerprint;
    private String nonce;
    private String fromFingerprint;
    private String nonsense = "";
    private String session;
    private final String expectedFromFingerprint;
    private boolean isOpen = false;
    private boolean didReceiveAnswer = false;
    private final Queue<IceCandidate> iceCandidateQueue = new LinkedList<>();

    SignalClient(SignalHandler handler, String toFingerprint, String nonce, String expectedFromFingerprint) {
        this.callback = handler;
        this.toFingerprint = toFingerprint;
        this.nonce = nonce;
        this.expectedFromFingerprint = expectedFromFingerprint;
        this.fromFingerprint = null;

//    this.fromFingerprint = fingerprint;

        client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();
    }

    private void setup() {
        Timber.tag(TAG).d("setup");
        iceCandidateQueue.clear();
        isOpen = false;
        didReceiveAnswer = false;
        final Request request = new Request.Builder()
                .url(SIGNAL_URL + "?id=" + fromFingerprint)
                .build();
        socket = client.newWebSocket(request, this);
    }

    public void close() {
        Timber.tag(TAG).d("close");
        isOpen = false;
        didReceiveAnswer = false;
        if (socket != null) {
            socket.close(NORMAL_CLOSURE_STATUS, "Goodbye!");
        }
    }

    void emitIceCandidate(IceCandidate iceCandidate) {
        Timber.tag(TAG).d("emit ice candidate");
        iceCandidateQueue.offer(iceCandidate);
        sendNextIceCandidate();
    }

    private void sendNextIceCandidate() {
        Timber.tag(TAG).d("send next ice candidate");
        if (socket != null && isOpen && didReceiveAnswer) {
            final IceCandidate iceCandidate = iceCandidateQueue.poll();
            if (iceCandidate != null) {
                final JSONObject jsonObject = new JSONObject();
                try {
                    final JSONObject candidate = SdpUtils.parseCandidate("a=" + iceCandidate.sdp);
                    if (candidate != null) {
                        final String ip = candidate.getString("ip");
                        if (ip != null && !ip.equals("127.0.0.1") && !ip.contains(":")) {
                            jsonObject.put("to", toFingerprint);
                            jsonObject.put("from", fromFingerprint);
                            jsonObject.put("type", "candidate");
                            jsonObject.put("sdpMLineIndex", iceCandidate.sdpMLineIndex);
                            jsonObject.put("candidate", candidate);
                            jsonObject.put("session", session);
                            jsonObject.put("nonsense", nonsense);

                            final String jsonString = jsonObject.toString().replace("\\/", "/");
                            socket.send(jsonString);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendNextIceCandidate();
            }
        }
    }

    void emitMessage(SessionDescription message) {
        Timber.tag(TAG).d("emit message");
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", message.type.canonicalForm());

            final JSONObject sdp =  SdpUtils.parseSdp(message.description);
            final String print = sdp.getJSONArray("contents").getJSONObject(0).getJSONObject("fingerprint").getString("print");
            final String newPrint = print.replace(":", "");

            if (!newPrint.equals(expectedFromFingerprint)) {
                return;
            }

            fromFingerprint = newPrint;

            if (nonce != null) {
                final String sense = toFingerprint + ":" + nonce + ":" + fromFingerprint;
                try {
                    nonsense = CertBuilder.hash256(sense).toUpperCase();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                nonsense = "";
            }

            this.session = fromFingerprint + "-" + toFingerprint + "-" + new Date().getTime();

            jsonObject.put("sdp", sdp);
            jsonObject.put("to", toFingerprint);
            jsonObject.put("from", fromFingerprint);
            jsonObject.put("session", session);
            jsonObject.put("nonsense", nonsense);
            jsonObject.put("retry", 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        setup();

        if (socket != null) {
            final String jsonString = jsonObject.toString().replace("\\/", "/"); // remove escaped slashes
            socket.send(jsonString);
        }

    }

    void remoteDescriptionComplete() {
        Timber.tag(TAG).d("remote description complete");
        didReceiveAnswer = true;
        sendNextIceCandidate();
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        Timber.tag(TAG).d("onOpen");
        isOpen = true;
        sendNextIceCandidate();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        Timber.tag(TAG).d("onMessage: %s", text);
        try {
            JSONObject data = new JSONObject(text);
            final String type = data.getString("type");
            if (type.equals("answer")) {
                callback.onAnswerReceived(data);
//        didReceiveAnswer = true;
//        sendNextIceCandidate();
            } else if (type.equals("candidate")) {
                callback.onIceCandidateReceived(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        Timber.tag(TAG).d("onMessage (bytes)");
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Timber.tag(TAG).d("onClosing: %s %s", code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        Timber.tag(TAG).e(t.getLocalizedMessage());
        callback.onFailure(t);
    }

}

