package com.altruismradio.api.server;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class ServerRequest{
    String method;
    String[] parameters;

    public ServerRequest(String method, String ... parameters){
        this.method = method;
        this.parameters = parameters;
    }

    public abstract void onSuccess(ServerResponse response);

    public abstract void onFailure(ServerException exception);
}
