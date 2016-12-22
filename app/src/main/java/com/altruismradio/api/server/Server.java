package com.altruismradio.api.server;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class Server {
    private final static String domain = "http://altruism.ml/";
    private final static String user_agent = "Altruism Radio Android App";

    public static void request(ServerRequestListener listener, String method, String ... parameters){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL obj = new URL(domain+"api/?method=about.api");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    con.setRequestProperty("User-Agent", user_agent);

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    //print result
                    System.out.println(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
