package com.altruismradio.api.server;

/**
 * Created by yuriy on 22.12.16.
 */
public class ServerException extends Exception{
    public int code;
    public String message;

    public ServerException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ServerException(Throwable cause) {
        super(cause);
        this.code = 0;
        this.message = "Undefined";
    }
}
