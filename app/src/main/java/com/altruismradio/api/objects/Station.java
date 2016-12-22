package com.altruismradio.api.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuriy on 22.12.16.
 */
public class Station {
    public int id;
    public String name;
    public Audio[] audios;
    public String[] imgs;
    public boolean async;

    public Station(int id, String name, Audio[] audios, String[] imgs, boolean async) {
        this.id = id;
        this.name = name;
        this.audios = audios;
        this.imgs = imgs;
        this.async = async;
    }

    public Station(JSONObject object) throws JSONException {
        if(object.has("id"))
            id = object.getInt("id");
        if(object.has("name"))
            name = object.getString("name");
        if(object.has("async"))
            async = object.getBoolean("async");
        if(object.has("audios")){
            JSONArray array = new JSONArray(object.getString("audios"));
            if(array.length()!=0){
                audios = new Audio[array.length()];
                for (int i=0; i<array.length(); i++){
                    audios[i] = new Audio(array.getJSONObject(i));
                }
            }
        }
        if(object.has("imgs")){
            JSONArray array = new JSONArray(object.getString("imgs"));
            if(array.length()!=0){
                imgs = new String[array.length()];
                for (int i=0; i<array.length(); i++){
                    imgs[i] = array.getString(i);
                }
            }
        }
    }
}
