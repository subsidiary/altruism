package com.altruismradio.api.server;

/**
 * Created by yuriy on 22.12.16.
 */
abstract class ServerRequestListener {

    abstract void onSuccess(ServerResponse response);

    abstract void onFailure(ServerException exception);
}
