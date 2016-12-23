package com.altruismradio.api.server;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class Server {
    private final static String domain = "http://altruism.ml/api/";
    private final static String user_agent = "Altruism Radio Android App";

    public static void request(@NonNull final ServerRequest request){
        if(request.method == null){
            request.onFailure(new ServerException(1,"Undefined method"));
            return;
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String url = domain+"?method="+request.method;
                    if (request.parameters != null) {
                        for (int i=0; i< request.parameters.length; i++){
                            if(i % 2 == 0)
                                url = url+"&"+request.parameters[i]+"=";
                            else
                                url = url+request.parameters[i];
                        }
                    }

                    URL obj = new URL(url);
                    long start = System.currentTimeMillis();
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    con.setRequestProperty("User-Agent", user_agent);

                    int responseCode = con.getResponseCode();
                    //response code don't know nahiba win nushen

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    request.onSuccess(new ServerResponse(response.toString(),System.currentTimeMillis()-start));
                }catch (Exception e){
                    request.onFailure(new ServerException(100, "Couldn't connect to the server"));
                }
            }
        }.start();
    }
}
