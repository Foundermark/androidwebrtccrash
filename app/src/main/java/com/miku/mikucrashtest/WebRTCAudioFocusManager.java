package com.miku.mikucrashtest;

import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.webrtc.AudioTrack;

public class WebRTCAudioFocusManager implements AudioManager.OnAudioFocusChangeListener {

    @NonNull
    private AudioManager audioManager;
    @Nullable
    private AudioTrack webRTCAudioTrack;
    private boolean isAudioPlaying = false;

    public WebRTCAudioFocusManager(@NonNull Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager == null) {
            throw new RuntimeException("can't get audio manager");
        } else {
            this.audioManager = audioManager;
        }
    }

    public synchronized void start(@NonNull AudioTrack webRTCAudioTrack) {
        stop();
        this.webRTCAudioTrack = webRTCAudioTrack;
        playAudio(false);
    }

    public synchronized void stop() {
        abandonAudioFocus();
        webRTCAudioTrack = null;
    }

    @Override
    public void onAudioFocusChange(int i) {
        if (webRTCAudioTrack == null) {
            return;
        }
        switch (i) {
            case AudioManager.AUDIOFOCUS_GAIN:
                playAudio(true);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                playAudio(false);
                break;
        }
    }

    public synchronized void requestAudioFocus() {
        if (webRTCAudioTrack != null && !isAudioPlaying) {
            int result = audioManager
                    .requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                playAudio(true);
            }
        }
    }

    public synchronized void abandonAudioFocus() {
        audioManager.abandonAudioFocus(this);
        playAudio(false);
    }

    private synchronized void playAudio(boolean playing) {
        if (webRTCAudioTrack != null) {
            // NOTE: Next line causes crash: A/libc: Fatal signal 11 (SIGSEGV), code 1, fault addr 0x6761726f747367 in tid 339 (signaling_threa)
            webRTCAudioTrack.setEnabled(playing);
            isAudioPlaying = playing;
        }
    }

}
