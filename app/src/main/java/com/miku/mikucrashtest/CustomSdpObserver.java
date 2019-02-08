package com.miku.mikucrashtest;

import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

public class CustomSdpObserver implements SdpObserver {

    private String tag;

    CustomSdpObserver(String logTag) {
        tag = this.getClass().getCanonicalName();
        this.tag = this.tag + " " + logTag;
    }

    @Override
    public void onCreateSuccess(SessionDescription sessionDescription) {
    }

    @Override
    public void onSetSuccess() {
    }

    @Override
    public void onCreateFailure(String s) {
    }

    @Override
    public void onSetFailure(String s) {
    }
}

