package com.altruismradio.api.server;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuriy on 22.12.16.
 */
public class ServerResponse extends JSONObject{
    public long time = -1;

    public ServerResponse(String json, long time) throws JSONException {
        super(json);
        this.time = time;
    }
}
