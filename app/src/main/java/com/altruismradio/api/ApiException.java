package com.altruismradio.api;

import com.altruismradio.api.server.ServerException;

/**
 * Created by yuriy on 22.12.16.
 */
public class ApiException extends Exception{
    public String message;
    public int code;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(Throwable cause) {
        super(cause);
        this.code = 0;
        try {
            ServerException e = (ServerException) cause;
            this.code = e.code;
        }catch (Exception ignore){}
        this.message = cause.getMessage();
    }



    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwException(Throwable exception, Object dummy) throws T {
        throw (T) exception;
    }
    public static void throwException(Throwable exception) {
        ApiException.<RuntimeException>throwException(new ApiException(exception), null);
    }
    public static void throwException(int code, String message) {
        ApiException.<RuntimeException>throwException(new ApiException(code, message), null);
    }
}
