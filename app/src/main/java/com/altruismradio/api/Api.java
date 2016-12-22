package com.altruismradio.api;

import com.altruismradio.api.server.Server;
import com.altruismradio.api.server.ServerException;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class Api {

    public static void init(){
        try {
            Server.request();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
