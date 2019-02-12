package com.miku.mikucrashtest;

import android.support.multidex.MultiDexApplication;

import timber.log.Timber;

public class MikuCrashTestApplication extends MultiDexApplication {

    private PeerConnectionClient peerConnectionClient;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        peerConnectionClient = new PeerConnectionClient(getApplicationContext());
    }

    public PeerConnectionClient peerConnectionClient() {
        return peerConnectionClient;
    }
}
