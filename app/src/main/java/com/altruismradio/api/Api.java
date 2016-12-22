package com.altruismradio.api;

import com.altruismradio.api.objects.Audio;
import com.altruismradio.api.objects.Station;
import com.altruismradio.api.server.Server;
import com.altruismradio.api.server.ServerException;
import com.altruismradio.api.server.ServerRequest;
import com.altruismradio.api.server.ServerResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class Api {


    public static void getStations(final ApiRequestListener listener) {
        getStations(listener, null);
    }
    public static void getStations(final ApiRequestListener listener, String fields){
        String[] parameters = null;
        if(fields!=null)
            try {
                parameters = new String[]{"hide_null_fields","1","list","1","fields", URLEncoder.encode(fields,"UTF-8")};
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        else
            parameters = new String[]{"hide_null_fields","1","list","1","fields","id,name,audios,imgs"};

        Server.request(new ServerRequest("stations.get",parameters){
            @Override
            public void onSuccess(ServerResponse response) {
                try {
                    if(response.getInt("code")==0){
                        listener.onFailure(new ApiException(
                                response.getJSONObject("response").getInt("error_code"),
                                response.getJSONObject("response").getString("error")
                        ));
                    }else{
                        JSONArray array = new JSONArray(response.getString("response"));
                        List<Station> stations = new ArrayList<Station>();
                        for(int i=0;i<array.length();i++)
                            stations.add(new Station(array.getJSONObject(i)));
                        listener.onSuccess(stations);
                    }
                } catch (JSONException e) {
                    listener.onFailure(new ApiException(e));
                }
            }

            @Override
            public void onFailure(ServerException exception) {
                listener.onFailure(new ApiException(exception));
            }
        });
    }



    public static void getStation(final ApiRequestListener listener, int id) {
        getStation(listener,id, null);
    }
    public static void getStation(final ApiRequestListener listener, int id, String fields){
        String[] parameters = null;
        if(fields!=null)
            try {
                parameters = new String[]{"hide_null_fields","1","id",id+"","fields", URLEncoder.encode(fields,"UTF-8")};
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        else
            parameters = new String[]{"hide_null_fields","1","id",id+"","fields","id,name,audios,imgs"};

        Server.request(new ServerRequest("stations.get",parameters){
            @Override
            public void onSuccess(ServerResponse response) {
                try {
                    if(response.getInt("code")==0){
                        listener.onFailure(new ApiException(
                                response.getJSONObject("response").getInt("error_code"),
                                response.getJSONObject("response").getString("error")
                        ));
                    }else{
                        listener.onSuccess(new Station(response.getJSONObject("response")));
                    }
                } catch (JSONException e) {
                    listener.onFailure(new ApiException(e));
                }
            }

            @Override
            public void onFailure(ServerException exception) {
                listener.onFailure(new ApiException(exception));
            }
        });
    }


    public static void getAudio(final ApiRequestListener listener, int id){
        getAudio(listener,id,null);
    }
    public static void getAudio(final ApiRequestListener listener, int id, String fields){
        String[] parameters = null;
        if(fields!=null)
            try {
                parameters = new String[]{"hide_null_fields","1","id",id+"","fields", URLEncoder.encode(fields,"UTF-8")};
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        else
            parameters = new String[]{"hide_null_fields","1","id",id+""};

        Server.request(new ServerRequest("audios.get",parameters){
            @Override
            public void onSuccess(ServerResponse response) {
                try {
                    if(response.getInt("code")==0){
                        listener.onFailure(new ApiException(
                                response.getJSONObject("response").getInt("error_code"),
                                response.getJSONObject("response").getString("error")
                        ));
                    }else{
                        listener.onSuccess(new Audio(response.getJSONObject("response")));
                    }
                } catch (JSONException e) {
                    listener.onFailure(new ApiException(e));
                }
            }

            @Override
            public void onFailure(ServerException exception) {
                listener.onFailure(new ApiException(exception));
            }
        });
    }
}
