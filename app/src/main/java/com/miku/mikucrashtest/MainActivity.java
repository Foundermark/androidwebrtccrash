package com.miku.mikucrashtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import static com.miku.mikucrashtest.ActivityRequestCodes.RECORD_AUDIO_REQUEST;

public class MainActivity extends AppCompatActivity {

    private TextureViewRenderer remoteVideoView;
    private PeerConnectionClient peerConnectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final MikuCrashTestApplication application = (MikuCrashTestApplication) getApplicationContext();
        peerConnectionClient = application.peerConnectionClient();

        remoteVideoView = findViewById(R.id.surface_view);

        final String userId = "1";
        final String deviceId = "012818890509";
        final String fingerprint = "C44505F32C8D03D3D1075844B4334988717A1C1A933EEC03E5158E3705AB662A";

        final String nonce = null;
        final boolean isPaired = true;
        peerConnectionClient.setCredentials(userId, deviceId, fingerprint, nonce, isPaired);

    }

    @Override
    protected void onResume() {
        super.onResume();
        peerConnectionClient.connect();
        peerConnectionClient.setRemoteVideoView(remoteVideoView);
        checkRecordAudioPermissions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        peerConnectionClient.disconnect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RECORD_AUDIO_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    peerConnectionClient.reconnect();
                }
            }
        }
    }

    private void checkRecordAudioPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                // show explanation
                requestRecordAudioPermissions();
            } else {
                requestRecordAudioPermissions();
            }
        }
    }

    private void requestRecordAudioPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_REQUEST);
    }

}
