package com.miku.mikucrashtest;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SdpUtils {
    @SuppressWarnings("unused")
    private static final String TAG = "SdpUtils";

    public static JSONObject parseSdp(String sdpString) {
        final JSONObject contentsObj = new JSONObject();

        try {
            contentsObj.put("contents", new JSONArray());
            final JSONObject sessionSDP = new JSONObject();
            sessionSDP.put("ice", new JSONObject());
            JSONObject sdpObj = sessionSDP;

            final String[] sdpLines = TextUtils.split(sdpString, "\r\n");
            for (String sline : sdpLines) {
//        Timber.d("line is " + sline);
                if (sline.length() == 0) break;
                final JSONObject line = parseLine(sline);

                final String type = line.getString("type");
                final String contents = line.getString("contents");
                if (type.equals("o")) {
                    contentsObj.put("session", parseO(contents));
                }

                if (type.equals("m")) {
                    final JSONObject media = parseM(contents);
                    sdpObj = new JSONObject();
                    sdpObj.put("candidates", new JSONArray());
                    sdpObj.put("codecs", new JSONArray());
                    sdpObj.put("sctpmap", new JSONArray());
                    sdpObj.put("ice", sessionSDP.getJSONObject("ice"));
                    final String fingerprint = sessionSDP.optString("fingerprint");
                    if (fingerprint != null) {
                        sdpObj.put("fingerprint", fingerprint);
                    }
                    sdpObj.put("media", media);
                    contentsObj.getJSONArray("contents").put(sdpObj);
                }

                if (type.equals("c")) {
                    sdpObj.put("connection", parseC(contents));
                }

                if (type.equals("a")) {
                    final JSONObject a = parseA(contents);
                    final JSONArray params = a.getJSONArray("params");
                    switch (a.getString("key")) {
                        case "candidate":
                            final JSONObject candidate = parseCandidate(params);
                            sdpObj.getJSONArray("candidates").put(candidate);
                            break;
                        case "group":
                            final JSONObject group = parseGroup(params);
                            contentsObj.put("group", group);
                            break;
                        case "setup":
                            final String setup = parseSetup(params);
                            sdpObj.put("setup", setup);
                            break;
                        case "mid":
                            final String mid = parseMid(params);
                            sdpObj.put("mid", mid);
                            break;
                        case "rtcp":
                            final JSONObject rtcp = parseRtcp(params);
                            sdpObj.put("rtcp", rtcp);
                            break;
                        case "rtcp-mux":
                            sdpObj.put("rtcp-mux", true);
                            break;
                        case "fmtp":
                            final JSONArray fmtpCodecs = sdpObj.optJSONArray("codecs");
                            if (fmtpCodecs != null) {
                                for (int i = 0; i < fmtpCodecs.length(); i++) {
                                    final JSONObject codec = fmtpCodecs.getJSONObject(i);
                                    if (codec.getString("id").equals(params.getString(0))) {
                                        final String fmtp = TextUtils.split(sline, " ")[1];
                                        codec.put("fmtp", fmtp);
                                        break;
                                    }
                                }
                            }
                            break;
                        case "rtcp-fb":
                            final JSONArray rtcpfb = new JSONArray();
                            for (int i = 1; i < params.length(); i++) {
                                rtcpfb.put(params.getString(i));
                            }
                            final JSONArray rtcpfbCodecs = sdpObj.optJSONArray("codecs");
                            if (rtcpfbCodecs != null) {
                                for (int i = 0; i < rtcpfbCodecs.length(); i++) {
                                    final JSONObject codec = rtcpfbCodecs.getJSONObject(i);
                                    if (codec.getString("id").equals(params.getString(0))) {
                                        final JSONArray rtcpfbs = codec.optJSONArray("rtcpfbs");
                                        if (rtcpfbs == null) {
                                            final JSONArray jsonArray = new JSONArray();
                                            jsonArray.put(rtcpfb);
                                            codec.put("rtcpfbs", jsonArray);
                                        } else {
                                            rtcpfbs.put(rtcpfb);
                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        case "rtpmap":
                            final JSONObject codec = parseRtpmap(params);
                            if (codec != null)
                                sdpObj.getJSONArray("codecs").put(codec);
                            break;
                        case "sendrecv":
                            sdpObj.put("direction", "sendrecv");
                            break;
                        case "sendonly":
                            sdpObj.put("direction", "sendonly");
                            break;
                        case "recvonly":
                            sdpObj.put("recvonly", "recvonly");
                            break;
                        case "ssrc-group":
                            sdpObj.put("ssrcgroup", params);
                            break;
                        case "ssrc":
                            sdpObj.put("ssrcs", parseSsrc(params, sdpObj.optJSONArray("ssrcs")));
                            break;
                        case "fingerprint":
                            final JSONObject print = parseFingerprint(params);
                            sdpObj.put("fingerprint", print);
                            break;
                        case "crypto":
                            final JSONObject crypto = parseCrypto(params);
                            sdpObj.put("crypto", crypto);
                            break;
                        case "ice-ufrag":
                            sdpObj.getJSONObject("ice").put("ufrag", params.getString(0));
                            break;
                        case "ice-pwd":
                            sdpObj.getJSONObject("ice").put("pwd", params.getString(0));
                            break;
                        case "ice-options":
                            sdpObj.getJSONObject("ice").put("options", params.getString(0));
                            break;
                        case "sctpmap":
                            final JSONObject sctp = parseSctpmap(params);
                            if (sctp != null)
                                sdpObj.getJSONArray("sctpmap").put(sctp);
                            break;
                        case "msid-semantic":
                            final JSONArray midsem = parseMidSem(params);
                            if (contentsObj.optJSONObject("group") != null) {
                                contentsObj.getJSONObject("group").put("midSem", midsem);
                            }
                            break;
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contentsObj;
    }

    public static JSONObject parseCandidate(String candidateSDP) {
        try {
            final JSONObject line = parseLine(candidateSDP);
            final String contents = line.optString("contents");
            if (contents != null) {
                final String substring = contents.substring(contents.indexOf(":") + 1);
                final String[] strings = TextUtils.split(substring, " ");
                final JSONArray jsonArray = new JSONArray();
                for (String string : strings) {
                    jsonArray.put(string);
                }
                return parseCandidate(jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String buildSdp(JSONObject contentsObj) {
        StringBuilder sdp = new StringBuilder("v=0\r\n");
        try {
            final JSONObject session = contentsObj.optJSONObject("session");
            if (session != null) {
                sdp.append("o=").append(session.getString("username")).append(" ").append(session.getString("id")).append(" ").append(session.getString("ver")).append(" ").append(session.getString("nettype")).append(" ").append(session.getString("addrtype")).append(" ").append(session.getString("address")).append("\r\n");
            } else {
                final long id = new Date().getTime();
                final int ver = 2;
                sdp.append("o=-").append(" 3").append(id).append(" ").append(ver).append(" IN IP4 192.67.4.14").append("\r\n");
            }

            sdp.append("s=-\r\n").append("t=0 0\r\n");

            final JSONObject connection = contentsObj.optJSONObject("connection");
            if (connection != null) {
                sdp.append("c=").append(connection.getString("nettype")).append(" ").append(connection.getString("addrtype")).append(" ").append(connection.getString("address")).append("\r\n");
            }

            final JSONObject group = contentsObj.optJSONObject("group");
            if (group != null) {
                sdp.append("a=group:").append(group.getString("type"));
                int ig = 0;
                while (ig + 1 <= group.getJSONArray("contents").length()) {
                    sdp.append(" ").append(group.getJSONArray("contents").getString(ig));
                    ig = ig + 1;
                }
                sdp.append("\r\n");
                final JSONArray midSem = group.optJSONArray("midSem");
                if (midSem != null) {
                    sdp.append("a=msid-semantic:");
                    int im = 0;
                    while (im + 1 <= midSem.length()) {
                        sdp.append(" ").append(midSem.getString(im));
                        im = im + 1;
                    }
                    sdp.append("\r\n");
                }
            }

            final JSONArray contents = contentsObj.optJSONArray("contents");
            int ic = 0;
            while (ic + 1 <= contents.length()) {
                final JSONObject sdpObj = contents.getJSONObject(ic);
                sdp.append(buildMedia(sdpObj));
                ic = ic + 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sdp.toString();
    }

    public static JSONObject patch(String sdpString, JSONObject acts) throws JSONException {
        final JSONArray patches = acts.optJSONArray("patches");
        if (patches == null) return acts;

        final String[] sdpLinesArray = TextUtils.split(sdpString, "\r\n");
        final List<String> sdpLines = new ArrayList<>(Arrays.asList(sdpLinesArray));

        for (int i = 0; i < patches.length(); i++) {
            final JSONObject patch = patches.getJSONObject(i);
            final String action = patch.getString("action");

            if (action.equals("append")) {
                final JSONArray lines = patch.getJSONArray("lines");
                final List<String> linesList = new ArrayList<>();
                for (int j = 0; j < lines.length(); j++) {
                    final String s = lines.getString(j);
                    linesList.add(s);
                }
                sdpLines.addAll(linesList);
            } else {

                int where = sdpLines.size();
                for (int sdpLine = 0; sdpLine < sdpLines.size(); sdpLine++) {
                    final String sline = sdpLines.get(sdpLine);
                    if (sline.startsWith(patch.getString("at"))) {
                        where = sdpLine;
                        break;
                    }
                }

                if (action.equals("prepend")) {
                    final String line = patch.getString("line");
                    if (line != null) {
                        sdpLines.add(where, line);
                    }
                    final JSONArray lines = patch.optJSONArray("lines");
                    if (lines != null) {
                        final List<String> plines = new ArrayList<>();
                        for (int j = 0; j < lines.length(); j++) {
                            final String s = lines.getString(j);
                            plines.add(s);
                        }
                        Collections.reverse(plines);
                        for (String pline : plines) {
                            sdpLines.add(where, pline);
                        }
                    }
                }

                if (action.equals("increment")) {
                    final String[] bits = TextUtils.split(sdpLines.get(where), " ");
                    final int field = patch.getInt("field");
                    String v = bits[field];
                    v = String.valueOf(Integer.parseInt(v) + 1);
                    bits[field] = "" + v;
                    final String line = TextUtils.join(" ", bits);
                    sdpLines.set(where, line);
                }

                if (action.equals("replace")) {
                    sdpLines.set(where, patch.getString("line"));
                }

                if (action.equals("duplicate")) {
                    String withline = null;
                    for (String sline : sdpLines) {
                        if (sline.startsWith(patch.getString("line"))) {
                            withline = sline;
                            break;
                        }
                    }
                    sdpLines.add(where, withline);
                }

            }
        }

        final List<String> stripped = new ArrayList<>();
        for (final String line : sdpLines) {
            if (line.length() > 0) {
                stripped.add(line);
            }
        }
        final String sdp = TextUtils.join("\r\n", stripped) + "\r\n";

        final JSONObject ret = new JSONObject();
        ret.put("type", acts.getString("type"));
        ret.put("sdp", sdp);
        return ret;
    }

    public static JSONObject addAvPatch(JSONObject mess) throws JSONException {
        final JSONArray patches = new JSONArray();

        //      final JSONObject info = mess.getJSONObject("info");
        final JSONObject info = mess.optJSONObject("vinfo");
        final JSONObject ainfo = mess.optJSONObject("ainfo");

        final JSONObject patch1 = new JSONObject();
        patch1.put("action", "increment");
        patch1.put("at", "o=-");
        patch1.put("field", 2);
        patches.put(patch1);

        if (info != null) {
            final JSONObject patch2 = new JSONObject();
            patch2.put("action", "replace");
            patch2.put("at", "a=group:BUNDLE");
//      patch2.put("line", "a=group:BUNDLE " + info.getString("datamid") + " video");
            patch2.put("line", "a=group:BUNDLE " + info.getString("datamid") + " video audio");
            patches.put(patch2);
        }


        final JSONObject patch3 = new JSONObject();
        patch3.put("action", "replace");
        patch3.put("at", "m=application");
        patch3.put("line", "m=application 9 DTLS/SCTP 5000");
        patches.put(patch3);

        if (info != null) {
            final JSONObject patch4 = new JSONObject();
            patch4.put("action", "append");
            patch4.put("at", "end");
            final JSONArray patch4Lines = new JSONArray();
            patch4Lines.put("m=video 9 RTP/SAVPF " + info.getString("vtype"));
            patch4Lines.put("a=mid:video");
            patch4Lines.put("a=sendonly");
            patch4Lines.put("a=rtcp-mux");
            patch4Lines.put("a=rtpmap:" + info.getString("vtype") + " " + info.getString("codec"));
            patch4Lines.put("a=fmtp:" + info.getString("vtype") + " packetization-mode=1;profile-level-id=42e01f");
//      patch4Lines.put("a=ssrc:" + info.getString("csrc") + " cname:drone");
            patch4Lines.put("a=ssrc:" + info.getString("csrc") + " cname:remotevideo");
            patch4Lines.put("a=ssrc:" + info.getString("csrc") + " mslabel:" + info.getString("msid"));
            patch4Lines.put("a=ssrc:" + info.getString("csrc") + " label:" + info.getString("appdata"));
            patch4Lines.put("a=ssrc:" + info.getString("csrc") + " msid:" + info.getString("msid") + " " + info.getString("appdata"));
            patch4.put("lines", patch4Lines);
            patches.put(patch4);
        }

        final JSONObject patch5 = new JSONObject();
        patch5.put("action", "duplicate");
        patch5.put("at", "a=mid:video");
        patch5.put("line", "a=fingerprint:");
        patches.put(patch5);

        final JSONObject patch6 = new JSONObject();
        patch6.put("action", "duplicate");
        patch6.put("at", "a=mid:video");
        patch6.put("line", "a=ice-ufrag:");
        patches.put(patch6);

        final JSONObject patch7 = new JSONObject();
        patch7.put("action", "duplicate");
        patch7.put("at", "a=mid:video");
        patch7.put("line", "a=ice-pwd:");
        patches.put(patch7);

        final JSONObject patch8 = new JSONObject();
        patch8.put("action", "duplicate");
        patch8.put("at", "a=mid:video");
        patch8.put("line", "a=setup:");
        patches.put(patch8);

        final JSONObject patch9 = new JSONObject();
        patch9.put("action", "duplicate");
        patch9.put("at", "a=mid:video");
        patch9.put("line", "c=IN");
        patches.put(patch9);

        // audio specific

        if (ainfo != null) {
            final JSONObject patch10 = new JSONObject();
            patch10.put("action", "append");
            patch10.put("at", "end");
            final JSONArray patch10Lines = new JSONArray();
            patch10Lines.put("m=audio 9 UDP/TLS/RTP/SAVP " + ainfo.getString("atype"));
            patch10Lines.put("a=mid:audio");
            patch10Lines.put("a=sendrcv");
            patch10Lines.put("a=rtcp-mux");
            patch10Lines.put("a=rtpmap:" + ainfo.getString("atype") + " " + ainfo.getString("codec"));
            patch10Lines.put("a=ssrc:" + ainfo.getString("csrc") + " cname:remoteaudio");
            patch10Lines.put("a=ssrc:" + ainfo.getString("csrc") + " mslabel:" + ainfo.getString("msid"));
            patch10Lines.put("a=ssrc:" + ainfo.getString("csrc") + " label:" + ainfo.getString("appdata"));
            patch10Lines.put("a=ssrc:" + ainfo.getString("csrc") + " msid:" + ainfo.getString("msid") + " " + ainfo.getString("appdata"));
            patch10.put("lines", patch10Lines);
            patches.put(patch10);
        }

        final JSONObject patch11 = new JSONObject();
        patch11.put("action", "duplicate");
        patch11.put("at", "mid:audio");
        patch11.put("line", "a=fingerprint:");
        patches.put(patch11);

        final JSONObject patch12 = new JSONObject();
        patch12.put("action", "duplicate");
        patch12.put("at", "mid:audio");
        patch12.put("line", "a=ice-ufrag:");
        patches.put(patch12);

        final JSONObject patch13 = new JSONObject();
        patch13.put("action", "duplicate");
        patch13.put("at", "mid:audio");
        patch13.put("line", "a=ice-pwd:");
        patches.put(patch13);

        final JSONObject patch14 = new JSONObject();
        patch14.put("action", "duplicate");
        patch14.put("at", "mid:audio");
        patch14.put("line", "a=setup:");
        patches.put(patch14);

        final JSONObject patch15 = new JSONObject();
        patch15.put("action", "duplicate");
        patch15.put("at", "mid:video");
        patch15.put("line", "c=IN");
        patches.put(patch15);

        mess.put("patches", patches);

        return mess;
    }

    public static String buildCandidate(JSONObject candidateObj) throws JSONException {
        return buildCandidate(candidateObj, new JSONObject());
    }

    private static JSONObject parseLine(String line) throws JSONException {
        final String[] s1 = TextUtils.split(line, "=");
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", s1[0]);
        jsonObject.put("contents", s1[1]);
        return jsonObject;
    }

    private static JSONObject parseO(String media) throws JSONException {
        final String[] s1 = TextUtils.split(media, " ");
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", s1[0]);
        jsonObject.put("id", s1[1]);
        jsonObject.put("ver", s1[2]);
        jsonObject.put("nettype", s1[3]);
        jsonObject.put("addrtype", s1[4]);
        jsonObject.put("address", s1[5]);
        return jsonObject;
    }

    private static JSONObject parseM(String media) throws JSONException {
        final String[] s1  = TextUtils.split(media, " ");
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", s1[0]);
        jsonObject.put("port", s1[1]);
        jsonObject.put("proto", s1[2]); // remove escaped characters
        final String substring = media.substring((s1[0] + s1[1] + s1[2]).length() + 3);
        final String[] strings = TextUtils.split(substring, " ");
        final JSONArray pts = new JSONArray();
        for (String string : strings) {
            pts.put(string);
        }
        jsonObject.put("pts", pts);
        return jsonObject;
    }

    private static JSONObject parseC(String media) throws JSONException {
        final String[] s1 = TextUtils.split(media, " ");
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("nettype", s1[0]);
        jsonObject.put("addrtype", s1[1]);
        jsonObject.put("address", s1[2]);
        return jsonObject;
    }

    private static JSONObject parseA(String attribute) throws JSONException {
        final String[] s1 = TextUtils.split(attribute, ":");
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", s1[0]);
        final String substring = attribute.substring(attribute.indexOf(":") + 1);
        final String[] strings = TextUtils.split(substring, " ");
        final JSONArray params = new JSONArray();
        for (String string : strings) {
            params.put(string);
        }
        jsonObject.put("params", params);
        return jsonObject;
    }

    private static JSONObject parseCandidate(JSONArray params) throws JSONException {
        final JSONObject candidate = new JSONObject();
        candidate.put("foundation", params.getString(0));
        candidate.put("component", params.getString(1));
        candidate.put("protocol", params.getString(2).toLowerCase());
        candidate.put("priority", params.getString(3));
        candidate.put("ip", params.getString(4));
        candidate.put("port", params.getString(5));
        int index = 6;
        while (index + 1 <= params.length()) {
            if (params.getString(index).equals("typ"))
                candidate.put("type", params.getString(index + 1));
            if (params.getString(index).equals("generation"))
                candidate.put("generation", params.getString(index + 1));
            if (params.getString(index).equals("username"))
                candidate.put("username", params.getString(index + 1));
            if (params.getString(index).equals("password"))
                candidate.put("password", params.getString(index + 1));
            if (params.getString(index).equals("raddr"))
                candidate.put("raddr", params.getString(index + 1));
            if (params.getString(index).equals("rport"))
                candidate.put("rport", params.getString(index + 1));
            index += 2;
        }
        return candidate;
    }

    private static JSONObject parseGroup(JSONArray params) throws JSONException {
        final JSONObject group = new JSONObject();
        group.put("type", params.getString(0));
        group.put("contents", new JSONArray());
        int index = 1;
        while (index + 1 <= params.length()) {
            group.getJSONArray("contents").put(params.getString(index));
            index = index + 1;
        }
        return group;
    }

    private static String parseSetup(JSONArray params) throws JSONException {
        return params.getString(0);
    }

    private static String parseMid(JSONArray params) throws JSONException {
        return params.getString(0);
    }

    private static JSONObject parseRtcp(JSONArray params) throws JSONException {
        final JSONObject rtcp = new JSONObject();
        rtcp.put("port", params.getString(0));
        if (params.length() > 1) {
            rtcp.put("nettype", params.getString(1));
            rtcp.put("addrtype", params.getString(2));
            rtcp.put("address", params.getString(3));
        }
        return rtcp;
    }

    private static JSONObject parseRtpmap(JSONArray params) throws JSONException {
        final String[] bits = TextUtils.split(params.getString(1), "/");
        final JSONObject codec = new JSONObject();
        codec.put("id", params.getString(0));
        codec.put("name", bits[0]);
        codec.put("clockrate", bits[1]);
        if (bits.length > 2) {
            codec.put("channels", bits[2]);
        }
        return codec;
    }

    private static JSONArray parseSsrc(JSONArray params, JSONArray ssrcs) throws JSONException {
        if (ssrcs == null)
            ssrcs = new JSONArray();
        final String sid = params.getString(0);

        JSONObject ssrcObj = null;
        for (int i = 0; i < ssrcs.length(); i++) {
            final JSONObject s = ssrcs.getJSONObject(i);
            if (s.getString("ssrc").equals(sid)) {
                ssrcObj = s;
                break;
            }
        }
        if (ssrcObj == null) {
            ssrcObj = new JSONObject();
            ssrcObj.put("ssrc", sid);
            ssrcs.put(ssrcObj);
        }
        final String value = params.getString(1);
        final String key = TextUtils.split(value, ":")[0];
        ssrcObj.put(key, TextUtils.split(value, ":")[1]);
        if (key.equals("msid") && params.getString(2) != null) {
            ssrcObj.put("msid3", params.getString(2));
        }
        return ssrcs;
    }

    private static JSONObject parseFingerprint(JSONArray params) throws JSONException {
        final JSONObject finger = new JSONObject();
        finger.put("hash", params.getString(0));
        finger.put("print", params.getString(1));
        finger.put("required", "1");
        return finger;
    }

    private static JSONObject parseCrypto(JSONArray params) throws JSONException {
        final JSONObject crypto = new JSONObject();
        crypto.put("tag", params.getString(0));
        crypto.put("crypto-suite", params.getString(1));
        crypto.put("key-params", params.getString(2));
        return crypto;
    }

    private static JSONObject parseSctpmap(JSONArray params) throws JSONException {
        final JSONObject dc = new JSONObject();
        dc.put("port", params.getString(0));
        dc.put("app", params.getString(1));
        dc.put("count", params.getString(2));
        return dc;
    }

    private static JSONArray parseMidSem(JSONArray params) {
        return params;
    }

    private static String buildMedia(JSONObject sdpObj) throws JSONException {
        StringBuilder sdp = new StringBuilder();
        sdp.append("m=").append(sdpObj.getJSONObject("media").getString("type")).append(" ").append(sdpObj.getJSONObject("media").getString("port")).append(" ").append(sdpObj.getJSONObject("media").getString("proto"));
        int mi = 0;
        while (mi + 1 <= sdpObj.getJSONObject("media").getJSONArray("pts").length()) {
            final String pts = sdpObj.getJSONObject("media").getJSONArray("pts").getString(mi);
            boolean containsCodec = false;
            final JSONArray codecs = sdpObj.getJSONArray("codecs");
            for (int i = 0; i < codecs.length(); i++) {
                final JSONObject c = codecs.getJSONObject(i);
                if (pts.equals(c.getString("id"))) containsCodec = true;
            }
            if (sdpObj.getJSONObject("media").getString("type").equals("application")
                    || containsCodec) {
                sdp.append(" ").append(pts);
            }
            mi = mi + 1;
        }
        sdp.append("\r\n");

        final JSONObject fingerprint = sdpObj.optJSONObject("fingerprint");
        if (fingerprint != null) {
            sdp.append(buildFingerprint(fingerprint));
        }

        final JSONObject ice = sdpObj.getJSONObject("ice");
        if (ice != null) {
            if (ice.opt("filterLines") == null) {
                sdp.append("a=ice-ufrag:").append(ice.getString("ufrag")).append("\r\n");
                sdp.append("a=ice-pwd:").append(ice.getString("pwd")).append("\r\n");
            }
            final String options = ice.optString("options");
            if (options != null) {
                sdp.append("a=ice-options:").append(options).append("\r\n");
            }
        }

        final JSONObject connection = sdpObj.optJSONObject("connection");
        if (connection != null) {
            sdp.append("c=").append(connection.getString("nettype")).append(" ").append(connection.getString("addrtype")).append(" ").append("0.0.0.0\r\n");
        }

        final String mid = sdpObj.optString("mid");
        if (mid != null) {
            sdp.append("a=mid:").append(mid).append("\r\n");
        }

        final String setup = sdpObj.optString("setup");
        if (setup != null) {
            sdp.append("a=setup:").append(setup).append("\r\n");
        }

        final JSONObject rtcp = sdpObj.optJSONObject("rtcp");
        if (rtcp != null) {
            sdp.append("a=rtcp:").append(rtcp.getString("port")).append(" ").append(rtcp.getString("nettype")).append(" ").append(rtcp.getString("addrtype")).append(" ").append(rtcp.getString("address")).append("\r\n");
        }

        int ci = 0;
        while (ci + 1 <= sdpObj.getJSONArray("candidates").length()) {
            sdp.append("a=").append(buildCandidate(sdpObj.getJSONArray("cendidates").getJSONObject(ci), sdpObj.optJSONObject("ice")));
            ci = ci + 1;
        }

        final String direction = sdpObj.optString("direction");
        if (direction != null) {
            switch (direction) {
                case "recvonly":
                    sdp.append("a=recvonly\r\n");
                    break;
                case "sendonly":
                    sdp.append("a=sendonly\r\n");
                    break;
                case "none":
                    sdp = new StringBuilder(sdp.toString());
                    break;
                default:
                    sdp.append("a=sendrecv\r\n");
                    break;
            }
        }

        if (sdpObj.opt("rtcp-mux") != null) {
            sdp.append("a=rtcp-mux").append("\r\n");
        }

        final JSONObject crypto = sdpObj.optJSONObject("crypto");
        if (crypto != null) {
            sdp.append(buildCrypto(crypto));
        }

        final JSONArray codecs = sdpObj.getJSONArray("codecs");
        int cdi = 0;
        while (cdi + 1 <= codecs.length()) {
            sdp.append(buildCodec(codecs.getJSONObject(cdi)));
            cdi = cdi + 1;
        }

        final JSONArray sctpmap = sdpObj.getJSONArray("sctpmap");
        int sdi = 0;
        while (sdi + 1 <= sctpmap.length()) {
            sdp.append(buildSctpmap(sctpmap.getJSONObject(sdi)));
            sdi = sdi + 1;
        }

        final JSONArray ssrcgroup = sdpObj.optJSONArray("ssrcgroup");
        if (ssrcgroup != null) {
            StringBuilder gline = new StringBuilder("a=ssrc-group:");
            for (int i = 0; i < ssrcgroup.length(); i++) {
                gline.append(ssrcgroup.getString(i)).append(" ");
            }
            sdp.append(gline.toString().trim()).append("\r\n");
        }

        final JSONArray ssrcs = sdpObj.optJSONArray("ssrcs");
        if (ssrcs != null) {
            for (int i = 0; i < ssrcs.length(); i++) {
                final JSONObject ssrc = ssrcs.getJSONObject(i);
                final String cname = ssrc.optString("cname");
                if (cname != null)
                    sdp.append("a=ssrc:").append(ssrc.getString("ssrc")).append(" ").append("cname:").append(cname).append("\r\n");
                final String mslabel = ssrc.optString("mslabel");
                if (mslabel != null)
                    sdp.append("a=ssrc:").append(ssrc.getString("ssrc")).append(" ").append("mslabel:").append(mslabel).append("\r\n");
                final String label = ssrc.optString("label");
                if (label != null)
                    sdp.append("a=ssrc:").append(ssrc.getString("ssrc")).append(" ").append("label:").append(label).append("\r\n");
                final String msid = ssrc.optString("msid");
                if (msid != null)
                    sdp.append("a=ssrc:").append(ssrc.getString("ssrc")).append(" ").append("msid:").append(msid).append("\r\n");
            }
        }

        return sdp.toString();
    }

    private static String buildFingerprint(JSONObject fingerObj) throws JSONException {
        return "a=fingerprint:" + fingerObj.getString("hash") + " " + fingerObj.getString("print") + "\r\n";
    }

    private static String buildCandidate(JSONObject candidateObj, JSONObject iceObj) throws JSONException {
        String sdp = "candidate:" + candidateObj.getString("foundation") + " " + candidateObj.getString("component") + " " + candidateObj.getString("protocol").toLowerCase() + " " + candidateObj.getString("priority") + " " + candidateObj.getString("ip") + " " + candidateObj.getString("port");
        final String type = candidateObj.optString("type");
        if (type != null)
            sdp = sdp + " typ " + type;
        if (candidateObj.getInt("component") == 1)
            sdp = sdp + " name rtp";
        if (candidateObj.getInt("component") == 2)
            sdp = sdp + " name rtcp";
        sdp = sdp + " network_name en0";
        final String username = candidateObj.optString("username");
        final String password = candidateObj.optString("password");
        if (username != null && username.length() > 0 && password != null && password.length() > 0) {
            sdp = sdp + " username " + username;
            sdp = sdp + " password " + password;
            final String ufrag = iceObj.optString("ufrag");
            if (ufrag == null)
                iceObj.put("ufrag", username);
            final String pwd = iceObj.optString("pwd");
            if (pwd == null)
                iceObj.put("pwd", username);
        } else if (iceObj != null) {
            final String ufrag = iceObj.optString("ufrag");
            if (ufrag != null && ufrag.length() > 0)
                sdp = sdp + " username " + ufrag;
            final String pwd = iceObj.optString("pwd");
            if (pwd != null && pwd.length() > 0)
                sdp = sdp + " password " + pwd;
        }
        final String generation = candidateObj.optString("generation");
        if (generation != null && generation.length() > 0)
            sdp = sdp + " generation " + generation;
        final String raddr = candidateObj.optString("raddr");
        if (raddr != null && raddr.length() > 0)
            sdp = sdp + " raddr " + raddr;
        final String rport = candidateObj.optString("rport");
        if (rport != null && rport.length() > 0)
            sdp = sdp + " rport " + rport;
        sdp = sdp + "\r\n";
        return sdp;
    }

    private static String buildCrypto(JSONObject cryptoObj) throws JSONException {
        return "a=crypto:" + cryptoObj.getString("tag") + " " + cryptoObj.getString("crypto-suite") + " " + cryptoObj.getString("key-params") + "\r\n";
    }

    private static String buildCodec(JSONObject codecObj) throws JSONException {
        StringBuilder sdp = new StringBuilder("a=rtmap:" + codecObj.getString("id") + " " + codecObj.getString("name") + "/" + codecObj.getString("clockrate"));
        final String channels = codecObj.optString("channels");
        if (channels != null) {
            sdp.append("/").append(channels);
        }
        sdp.append("\r\n");
        final String ptime = codecObj.optString("ptime");
        if (ptime != null) {
            sdp.append("a=ptime:").append(ptime);
            sdp.append("\r\n");
        }
        final String fmtp = codecObj.optString("fmtp");
        if (fmtp != null) {
            sdp.append("a=fmtp:").append(codecObj.getString("id")).append(" ").append(fmtp).append("\r\n");
        }
        final JSONArray rtcpfbs = codecObj.optJSONArray("rtcpfbs");
        if (rtcpfbs != null) {
            for (int i = 0; i < rtcpfbs.length(); i++) {
                sdp.append("a=rtcp-fb:").append(codecObj.getString("id"));
                final JSONArray rtcpfb = rtcpfbs.getJSONArray(i);
                for (int j = 0; j < rtcpfb.length(); j++) {
                    sdp.append(" ").append(rtcpfb.getString(j));
                }
                sdp.append("\r\n");
            }
        }

        return sdp.toString();
    }

    private static String buildSctpmap(JSONObject sctpObj) throws JSONException {
        return "a=sctpmap:" + sctpObj.getString("port") + " " + sctpObj.getString("app") + " " + sctpObj.getString("count") + "\r\n";
    }

}
