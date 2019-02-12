package com.miku.mikucrashtest;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.DataChannel;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RendererCommon;
import org.webrtc.RtcCertificatePem;
import org.webrtc.RtpReceiver;
import org.webrtc.SessionDescription;
import org.webrtc.VideoTrack;
import org.webrtc.audio.AudioDeviceModule;
import org.webrtc.audio.JavaAudioDeviceModule;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import timber.log.Timber;

import static com.miku.mikucrashtest.Config.STUN_URI;
import static com.miku.mikucrashtest.Config.TURN_URI;

public class PeerConnectionClient implements SignalHandler {
    private static final String TAG = "PeerConnectionClient";
    private static final String AUDIO_TRACK_ID = "ARDAMSa0";
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final Context context;
    private EglBase rootEglBase;
    private SignalClient signalClient;
    private final MediaConstraints sdpConstraints = new MediaConstraints();
    private PeerConnection peerConnection = null;
    private DataChannel avDataChannel = null;
    private VideoTrack videoTrack;
    private TextureViewRenderer remoteVideoView;
    private RtcCertificatePem certificate;
    private boolean didSendOffer = false;
    private boolean isDevicePaired = false;
    private String fromFingerprint;
    private String deviceId;
    private boolean isInitialized = false;
    private MediaStream stream;
    private AudioTrack localAudioTrack;
    private AudioSource audioSource;

    public PeerConnectionClient(Context context) {
        this.context = context.getApplicationContext();
        rootEglBase = EglBase.create();

        final String pem = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtfc/lDsuFxc5P\n" +
                "0u9gudMMt8SHmwZgEvsx+xy2WBxxsKovucg8cM3YPle7V6Us17belpLHDmXuhgfL\n" +
                "+7EX6E7TDRqJE0xcXDQzvf6CdC6i5o4Sh/OLBEzsLzEtNijtWwR5gBgtsTWwTjDg\n" +
                "BJf4mo2+d+QuqfSPre8HuiRMYp82tVj0CxN61no0CWLwpyqe97RLIKixoIc/YnUn\n" +
                "IbUYF2ZfN1aZeUMd+fRxOSAc8Q335YCzTAqxHTKYyqAi/2WGzuukFgudS6VJ3Qv0\n" +
                "GTbAqQKJiKTcodHDJOY0D1NQ8ifR7Oau6NpzYT+FUOu4NqPQszHmzjixBDNOwEnY\n" +
                "j2GX65ZjAgMBAAECggEAN1YbQuTKrrLugNB3cMMkmXhd86DTUj6Lp6AAQvrsq6id\n" +
                "R9a6kuiqB3CvG/6zHQ/68JlXXK1vfrp6byorDMSYq8GQtqsUnpr8PYq6zJJRM71V\n" +
                "EH0ThcJL5MOSrGiLelWfIj/ktmJBDSiLd1CPxohJcLid6/JiopdoV+8b189XGCQN\n" +
                "6yvsP0EPZn2s6lrgpVjVFlToH6YBXECVnfm2FD4uSflHGX4zWrCywa5QHDjtcKIY\n" +
                "ZWw1iX7dQMqTs34v4Sp9ghje0IfH4xtXgIYtkl7O7C851yWui+ijxvN3qzkiWwoG\n" +
                "nC/EosFX/iJ2YhkY+H/vDUGN0HEcnCXuJkBdzOkuXQKBgQDhVK+HQFHWi0lLoRby\n" +
                "ncojInmjv4r2tM0O3EarucfcvaJ//30IHTZWyXxgvcX5PTV1gizjRPZlFUc3GYsx\n" +
                "xfAkLVXQ1HKBRKuGArq9PoErd1PRjzCSElfut/jBkgyB1O2iVvkz34q37PwnJ980\n" +
                "cTlWIaCui7CYElEdtWGXdfHWfwKBgQDFGtynbr2aaYmuDLFHY9BIyNu/jguIz3wI\n" +
                "MyqXQPO5K9JqAAcVPdBEc4wCDg0sI19bCl2VEj5FSPnWJHQIQsd9ZPnptMZM0lxJ\n" +
                "nGFoFKisIMKB/eMfcy9ZR7GM7i2VuPMS0KXSxQVug5LMI0D601Zr/3cLNVDNHrqx\n" +
                "ykX7fvi2HQKBgDnqt3cvLBnWEU/UJv7TWdpwCQ3ytLg2vqI8Mhn/lYSCZZs6wxtr\n" +
                "zsS94y9Gd/VZKz156Sm9VwuiTXy3HxT7CH9z+6PCh2/8g43dc7wirbbLZArwIA7b\n" +
                "kih0twaiEH7qiO36JvGTWqdtzV4PHIrxM0iVOiTDGO/tfVYCggQZN8DnAoGBALA4\n" +
                "eOiU4J1KGBbtMB40hgZc1eLn+zYA0tJ99GkwX/bA0CmHBs4d6rMYXyeDKpG4uksp\n" +
                "9EsP+W52dS/YfYjZM4PPoIQq5FPQi0QgtRGZzse9rkkMFWCL2dxHdd0bINRIe8Hp\n" +
                "myfakCWjKr6qEjFue0ipKORzcV9CRwYnYvQcK/6dAoGBANr3SbhoIH7bX/UVTwFh\n" +
                "Iklp09QzL7QPqmZysCDZ4kfQjQeMt2nnVh/o7pahbn/CvXad/iUQw77TUFj8m8KN\n" +
                "V2LnghHGcpak+JGvxPg/G7NzsHI1YFN+e9HORxzsvRzw70zdQDgpu68Q2e5Td3zK\n" +
                "4j9qwskgOB0xegS5/H5qFi0a\n" +
                "-----END PRIVATE KEY-----\n" +
                "|-----BEGIN CERTIFICATE-----\n" +
                "MIICnTCCAYUCCHxwpRPUkN8TMA0GCSqGSIb3DQEBCwUAMBExDzANBgNVBAMMBldl\n" +
                "YlJUQzAeFw0xOTAxMjIxODM1MzVaFw0yOTAxMjAxODM1MzVaMBExDzANBgNVBAMM\n" +
                "BldlYlJUQzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAK19z+UOy4XF\n" +
                "zk/S72C50wy3xIebBmAS+zH7HLZYHHGwqi+5yDxwzdg+V7tXpSzXtt6WkscOZe6G\n" +
                "B8v7sRfoTtMNGokTTFxcNDO9/oJ0LqLmjhKH84sETOwvMS02KO1bBHmAGC2xNbBO\n" +
                "MOAEl/iajb535C6p9I+t7we6JExinza1WPQLE3rWejQJYvCnKp73tEsgqLGghz9i\n" +
                "dSchtRgXZl83Vpl5Qx359HE5IBzxDfflgLNMCrEdMpjKoCL/ZYbO66QWC51LpUnd\n" +
                "C/QZNsCpAomIpNyh0cMk5jQPU1DyJ9Hs5q7o2nNhP4VQ67g2o9CzMebOOLEEM07A\n" +
                "SdiPYZfrlmMCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAKjpFDbD09Z/xO0vUgcWL\n" +
                "85k60RibgjHz6j9lHo+gPCFGxa41pGn/cMXdzyZfijC8qMULuRDnhyuc1wSd7t/s\n" +
                "gAiCSrZMv+Y5TNW4fBqPxAnfojlaD8WHKI1tR1oa1Onl3eHkmLHXM7TYqCQRtCiR\n" +
                "pC8Sa+SmcH7pTnTqDshCj2q8YxPsu2SNaLUuMd4qk2jw2JZY05vQlN04FMhZISNs\n" +
                "pKeNHsLtp+BUtei6canYrv1xKfDHbE114FmFzLqH6vf4TshYLCrPFvS6wTITgfoo\n" +
                "POSstTpsMd+k+bdlIUJ3lv4uJjTpkZF1zlH8GkRNVlWh/TBD4UPLLmSeeob0n1VX\n" +
                "bw==\n" +
                "-----END CERTIFICATE-----";
        final String[] keys = TextUtils.split(pem, "\\|");
        this.certificate = new RtcCertificatePem(keys[0], keys[1]);
        this.fromFingerprint = "609F78960011655CC618A4DB48B71894F8C1ECCC24AC7A2EE69A9CDDA0D4D34A";
    }

    public void setCredentials(String userId, String deviceId, String toFingerprint, String nonce, boolean isPaired) {
        Timber.tag(TAG).d("set credentials userId=%s deviceId=%s isPaired=%s", userId, deviceId, isPaired);
        this.isDevicePaired = isPaired;
        Timber.tag(TAG).d("comparing device id %s to new device id %s", this.deviceId, deviceId);
        if (this.deviceId == null || !this.deviceId.equals(deviceId)) {
            isInitialized = false;
            this.deviceId = deviceId;
            Timber.tag(TAG).d("toFingerprint=%s", toFingerprint);
            if (toFingerprint != null) {
                if (signalClient != null) {
                    signalClient.close();
                    signalClient = null;
                }
                signalClient = new SignalClient(this, toFingerprint, nonce, fromFingerprint);
                executor.execute(this::checkConfig);
            } else {
                executor.execute(this::close);
            }
        }
    }

    public void connect() {
        Timber.tag(TAG).d("connect");
        if (videoTrack != null) {
            videoTrack.setEnabled(true);
        }
    }

    public void disconnect() {
        Timber.tag(TAG).d("disconnect");
        if (remoteVideoView != null) {
            if (videoTrack != null) {
                videoTrack.removeSink(remoteVideoView);
            }
            remoteVideoView.release();
            remoteVideoView = null;
        }

        if (videoTrack != null) {
            videoTrack.setEnabled(false);
        }
    }

    public void reconnect() {
        Timber.tag(TAG).d("reconnect");
        executor.execute(this::checkConfig);
    }

    public void setRemoteVideoView(TextureViewRenderer remoteVideoView) {
        Timber.tag(TAG).d("set remote video view");
        if (this.remoteVideoView == null) {
            Timber.tag(TAG).d("previous video view is null, set it");
            this.remoteVideoView = remoteVideoView;
            this.remoteVideoView.init(rootEglBase.getEglBaseContext(), new RendererCommon.RendererEvents() {
                @Override
                public void onFirstFrameRendered() {

                }

                @Override
                public void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {
                }
            });
            executor.execute(() -> {
                if (videoTrack != null) {
                    Timber.tag(TAG).d("add video sink");
                    videoTrack.addSink(remoteVideoView);
                }
                checkConfig();
            });
        }
    }

    private void checkConfig() {
        Timber.tag(TAG).d("check config remoteVideoView=%s signalClient=%s isInitialized=%s isDevicePaired=%s", remoteVideoView != null, signalClient != null, isInitialized, isDevicePaired);
        if (remoteVideoView != null && signalClient != null && !isInitialized && isDevicePaired) {
            executor.execute(this::createPeerConnection);
        }
    }

    private void createPeerConnection() {
        Timber.tag(TAG).d("create peer connection");
        close();
        init();
        createVideoDataChannel();
    }

    private void close() {
        Timber.tag(TAG).d("close");

        if (avDataChannel != null) {
            avDataChannel.close();
            avDataChannel.dispose();
            avDataChannel = null;
        }

        if (peerConnection != null) {
            peerConnection.close();
            peerConnection.dispose();
            peerConnection = null;
        }

        if (audioSource != null) {
            audioSource.dispose();
            audioSource = null;
        }

        if (videoTrack != null) {
            if (remoteVideoView != null) {
                videoTrack.removeSink(remoteVideoView);
            }
            videoTrack = null;
        }

        if (signalClient != null) {
            signalClient.close();
        }
    }

    private void init() {
        Timber.tag(TAG).d("init");

        final List<PeerConnection.IceServer> peerIceServers = new ArrayList<>();

        final PeerConnection.IceServer stunServer = PeerConnection.IceServer.builder(STUN_URI) // miku
                .setTlsCertPolicy(PeerConnection.TlsCertPolicy.TLS_CERT_POLICY_INSECURE_NO_CHECK)
                .createIceServer();
        peerIceServers.add(stunServer);

        final PeerConnection.IceServer turnServer = PeerConnection.IceServer.builder(TURN_URI) // miku
                .setUsername("miku").setPassword("43r98wefjnskfno4") // miku
                .setTlsCertPolicy(PeerConnection.TlsCertPolicy.TLS_CERT_POLICY_INSECURE_NO_CHECK)
                .createIceServer();
        peerIceServers.add(turnServer);

        // Handle audio devices
        final AudioDeviceModule adm = createJavaAudioDevice();

        final PeerConnectionFactory.InitializationOptions initializationOptions = PeerConnectionFactory.InitializationOptions.builder(context.getApplicationContext())
                .setFieldTrials("false")
                .createInitializationOptions();
        PeerConnectionFactory.initialize(initializationOptions);

        final PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        final DefaultVideoEncoderFactory defaultVideoEncoderFactory = new DefaultVideoEncoderFactory(rootEglBase.getEglBaseContext(), true, true);
        final DefaultVideoDecoderFactory defaultVideoDecoderFactory = new DefaultVideoDecoderFactory(rootEglBase.getEglBaseContext());
        final PeerConnectionFactory peerConnectionFactory = PeerConnectionFactory.builder()
                .setOptions(options)
                .setAudioDeviceModule(adm)
                .setVideoEncoderFactory(defaultVideoEncoderFactory)
                .setVideoDecoderFactory(defaultVideoDecoderFactory)
                .createPeerConnectionFactory();

        final MediaConstraints audioConstraints = new MediaConstraints();

        audioSource = peerConnectionFactory.createAudioSource(audioConstraints);
        localAudioTrack = peerConnectionFactory.createAudioTrack(AUDIO_TRACK_ID, audioSource); // 101 previously
        localAudioTrack.setEnabled(false); // muted for testing
        stream = peerConnectionFactory.createLocalMediaStream("ARDAMS"); // 102 previously
        stream.addTrack(localAudioTrack);

        final PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(peerIceServers);
        rtcConfig.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.DISABLED;
        rtcConfig.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        rtcConfig.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        rtcConfig.keyType = PeerConnection.KeyType.ECDSA;
        rtcConfig.certificate = this.certificate;

        didSendOffer = false;
        peerConnection = null;
        peerConnection = peerConnectionFactory.createPeerConnection(rtcConfig, new PeerConnection.Observer() {
            @Override
            public void onSignalingChange(PeerConnection.SignalingState signalingState) {
                Timber.tag(TAG).d("onSignalingChange: %s", signalingState);
            }

            @Override
            public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
                Timber.tag(TAG).d("onIceConnectionChange: %s", iceConnectionState);
            }

            @Override
            public void onIceConnectionReceivingChange(boolean b) {
                Timber.tag(TAG).d("onIceCOnnectionReceivingChange: %s", b);
            }

            @Override
            public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
                Timber.tag(TAG).d("onIceGatheringChange: %s", iceGatheringState);
            }

            @Override
            public void onIceCandidate(IceCandidate iceCandidate) {
                Timber.tag(TAG).d("onIceCandidate: %s", iceCandidate);
                executor.execute(() -> {
                    if (signalClient != null) signalClient.emitIceCandidate(iceCandidate);
                });
            }

            @Override
            public void onIceCandidatesRemoved(IceCandidate[] iceCandidates) {
                Timber.tag(TAG).d("onIceCandidatesRemoved");
            }

            @Override
            public void onAddStream(MediaStream mediaStream) {
                Timber.tag(TAG).d("onAddStream: %s", mediaStream);
                executor.execute(() -> addMediaStream(mediaStream));
            }

            @Override
            public void onRemoveStream(MediaStream mediaStream) {
                Timber.tag(TAG).d("onRemoveStream: %s", mediaStream);
            }

            @Override
            public void onDataChannel(DataChannel dataChannel) {
                Timber.tag(TAG).d("onDataChannel: %s", dataChannel);
            }

            @Override
            public void onRenegotiationNeeded() {
                Timber.tag(TAG).d("onRenegotiationNeeded");
                executor.execute(() -> {
                    if (!didSendOffer) {
                        createOffer();
                    }
                });
            }

            @Override
            public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreams) {
                Timber.tag(TAG).d("onAddTrack: %s, %s", rtpReceiver, mediaStreams);
                executor.execute(() -> addMediaStreams(mediaStreams));
            }

        });

    }

    private AudioDeviceModule createJavaAudioDevice() {
        Timber.tag(TAG).d("create java audio device");

        // Set audio record error callbacks.
        JavaAudioDeviceModule.AudioRecordErrorCallback audioRecordErrorCallback = new JavaAudioDeviceModule.AudioRecordErrorCallback() {
            @Override
            public void onWebRtcAudioRecordInitError(String errorMessage) {
                reportError(errorMessage);
            }

            @Override
            public void onWebRtcAudioRecordStartError(
                    JavaAudioDeviceModule.AudioRecordStartErrorCode errorCode, String errorMessage) {
                reportError(errorMessage);
            }

            @Override
            public void onWebRtcAudioRecordError(String errorMessage) {
                reportError(errorMessage);
            }
        };

        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = new JavaAudioDeviceModule.AudioTrackErrorCallback() {
            @Override
            public void onWebRtcAudioTrackInitError(String errorMessage) {
                reportError(errorMessage);
            }

            @Override
            public void onWebRtcAudioTrackStartError(
                    JavaAudioDeviceModule.AudioTrackStartErrorCode errorCode, String errorMessage) {
                reportError(errorMessage);
            }

            @Override
            public void onWebRtcAudioTrackError(String errorMessage) {
                reportError(errorMessage);
            }
        };
        changeDefaultMediaTypeInAudioTrack();
        return JavaAudioDeviceModule.builder(this.context)
                .setSamplesReadyCallback(null)
                .setUseHardwareAcousticEchoCanceler(true)
                .setUseHardwareNoiseSuppressor(true)
                .setAudioRecordErrorCallback(audioRecordErrorCallback)
                .setAudioTrackErrorCallback(audioTrackErrorCallback)
                .createAudioDeviceModule();

    }

    private void changeDefaultMediaTypeInAudioTrack() {
        Timber.tag(TAG).d("change default media type in audio track");
        try {
            Class<?> clazz = Class.forName("org.webrtc.audio.WebRtcAudioTrack");
            Field defaultUsageField = clazz.getDeclaredField("DEFAULT_USAGE");
            defaultUsageField.setAccessible(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                defaultUsageField.setInt(null, AudioAttributes.USAGE_MEDIA);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void reportError(final String errorMessage) {
        Timber.tag(TAG).e(errorMessage);
    }

    private void addMediaStreams(MediaStream[] mediaStreams) {
        Timber.tag(TAG).d("add media streams");
        for (MediaStream mediaStream : mediaStreams) {
            addMediaStream(mediaStream);
        }
    }

    private void addMediaStream(MediaStream mediaStream) {
        Timber.tag(TAG).d("add media stream");
        if (!mediaStream.videoTracks.isEmpty()) {
            videoTrack = mediaStream.videoTracks.get(0);
            if (remoteVideoView != null) {
                videoTrack.removeSink(remoteVideoView);
                videoTrack.addSink(remoteVideoView);
            }

        }
    }

    private void createOffer() {
        Timber.tag(TAG).d("create offer");
        if (peerConnection != null) {
            didSendOffer = true;
            peerConnection.createOffer(new CustomSdpObserver("localCreateOffer") {
                @Override
                public void onCreateSuccess(SessionDescription sessionDescription) {
                    super.onCreateSuccess(sessionDescription);
                    peerConnection.setLocalDescription(new CustomSdpObserver("localSetLocalDesc") {
                        @Override
                        public void onSetSuccess() {
                            super.onSetSuccess();
                            executor.execute(() -> {
                                if (signalClient != null)
                                    signalClient.emitMessage(sessionDescription);
                            });
                        }
                    }, sessionDescription);
                }
            }, sdpConstraints);
        }
    }

    private void createVideoDataChannel() {
        Timber.tag(TAG).d("create video data channel");
        if (peerConnection == null) {
            return;
        }
        if (avDataChannel != null) {
            return;
        }
        avDataChannel = peerConnection.createDataChannel("avrelay", new DataChannel.Init());
        if (avDataChannel == null) {
            return;
        }
        avDataChannel.registerObserver(new DataChannel.Observer() {
            @Override
            public void onBufferedAmountChange(long l) {
            }

            @Override
            public void onStateChange() {
                if (avDataChannel != null) {
                    switch (avDataChannel.state()) {
                        case CONNECTING:
                            break;
                        case OPEN:
                            executor.execute(() -> connectVideo());
                            break;
                        case CLOSING:
                            break;
                        case CLOSED:
                            break;
                    }
                }
            }

            @Override
            public void onMessage(DataChannel.Buffer buffer) {
                executor.execute(() -> {
                    final String message = ByteBufferUtils.byteBufferToString(buffer.data, Charset.defaultCharset());
                    try {
                        final JSONObject jsonObject = new JSONObject(message);
                        final String type = jsonObject.getString("type");
                        final String offer = SessionDescription.Type.OFFER.canonicalForm();
                        if (type.equals(offer)) {
                            patchVideo(jsonObject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    private void connectVideo() {
        Timber.tag(TAG).d("connect video");
        peerConnection.addStream(stream);

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "upgrade");
            jsonObject.put("time", new Date().getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String jsonString = jsonObject.toString();

        final ByteBuffer data = ByteBufferUtils.stringToByteBuffer(jsonString, Charset.defaultCharset());
        if (avDataChannel != null) avDataChannel.send(new DataChannel.Buffer(data, false));
    }

    private void patchVideo(JSONObject jsonObject) throws JSONException {
        Timber.tag(TAG).d("patch video");
        final JSONObject patch = SdpUtils.addAvPatch(jsonObject);
        final SessionDescription remoteDescription = peerConnection.getRemoteDescription();
        if (remoteDescription != null) {
            final JSONObject patched = SdpUtils.patch(remoteDescription.description, patch);
            final String type = patched.getString("type");
            final String sdp = patched.getString("sdp").replace("\\/", "/");
            final SessionDescription patchedSessionDescription = new SessionDescription(SessionDescription.Type.fromCanonicalForm(type), sdp);
            executor.execute(() -> setNewOffer(patchedSessionDescription));
        }
    }

    private void setNewOffer(SessionDescription patchedSessionDescription) {
        Timber.tag(TAG).d("set new offer");
        if (peerConnection == null) return;
        peerConnection.setRemoteDescription(new CustomSdpObserver("patchSetRemoteDesc") {
            @Override
            public void onSetSuccess() {
                super.onSetSuccess();
                peerConnection.createAnswer(new CustomSdpObserver("patchCreateAnswer") {
                    @Override
                    public void onCreateSuccess(SessionDescription sessionDescription) {
                        super.onCreateSuccess(sessionDescription);
                        executor.execute(() -> setVideoLocalDescription(sessionDescription));
                    }
                }, sdpConstraints);
            }
        }, patchedSessionDescription);
    }

    private void setVideoLocalDescription(SessionDescription sessionDescription) {
        Timber.tag(TAG).d("set video local description");
        if (peerConnection == null) return;
        peerConnection.setLocalDescription(new CustomSdpObserver("patchLocalCreateAnswer") {
            @Override
            public void onSetSuccess() {
                super.onSetSuccess();
                executor.execute(() -> sendVideoLocalDescription());
            }
        }, sessionDescription);
    }

    private void sendVideoLocalDescription() {
        Timber.tag(TAG).d("send video local description");
        if (peerConnection == null) return;
        final SessionDescription sessionDescription = peerConnection.getLocalDescription();
        if (sessionDescription == null) return;

        final JSONObject mess = new JSONObject();
        try {
            mess.put("type", sessionDescription.type.canonicalForm());
            mess.put("sdp", sessionDescription.description);
            mess.put("tick", new Date().getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String jsonString = mess.toString();
        final ByteBuffer data = ByteBufferUtils.stringToByteBuffer(jsonString, Charset.defaultCharset());
        if (avDataChannel != null) avDataChannel.send(new DataChannel.Buffer(data, false));
    }

    @Override
    public void onAnswerReceived(JSONObject data) {
        Timber.tag(TAG).d("on answer received");
        try {
            final String type = data.getString("type");
            final String sdp = SdpUtils.buildSdp(data.getJSONObject("sdp"));
            final SessionDescription sessionDescription = new SessionDescription(SessionDescription.Type.fromCanonicalForm(type), sdp);
            executor.execute(() -> setAnswer(sessionDescription));
        } catch (JSONException e) {
            Timber.tag(TAG).e(e.getLocalizedMessage());
        }
    }

    @Override
    public void onIceCandidateReceived(JSONObject data) {
        Timber.tag(TAG).d("on ice candidate received");
        try {
            final String candidate = SdpUtils.buildCandidate(data.getJSONObject("candidate"));
            final String sdpMLineIndexString = data.getString("sdpMLineIndex");
            final int sdpMLineIndex = Integer.parseInt(sdpMLineIndexString);
            final IceCandidate iceCandidate = new IceCandidate("data", sdpMLineIndex, candidate);
            executor.execute(() -> setCandidate(iceCandidate));
        } catch (JSONException e) {
            Timber.tag(TAG).e(e.getLocalizedMessage());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Timber.tag(TAG).e("on failure");
        isInitialized = false;
    }

    private void setAnswer(SessionDescription sessionDescription) {
        Timber.tag(TAG).d("set answer");
        if (peerConnection != null) {
            peerConnection.setRemoteDescription(new CustomSdpObserver("localSetRemoteDesc") {
                @Override
                public void onSetSuccess() {
                    super.onSetSuccess();
                    executor.execute(() -> signalClient.remoteDescriptionComplete());
                }
            }, sessionDescription);
        }
    }

    private void setCandidate(IceCandidate iceCandidate) {
        Timber.tag(TAG).d("set candidate");
        if (peerConnection != null) {
            final boolean success = peerConnection.addIceCandidate(iceCandidate);
            Timber.tag(TAG).d("set ice candidate success: %s", success);
        }
    }

}
