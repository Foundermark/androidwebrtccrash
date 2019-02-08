package com.miku.mikucrashtest;

import org.json.JSONObject;

public interface SignalHandler {
    void onAnswerReceived(JSONObject data);
    void onIceCandidateReceived(JSONObject data);
    void onFailure(Throwable t);
}

