package com.altruismradio.api.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuriy on 22.12.16.
 */
public class Audio {
    public int id;
    public String name, artist, url;
    public String[] imgs;

    public Audio(int id, String name, String artist, String url, String[] imgs) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.url = url;
        this.imgs = imgs;
    }

    public Audio(JSONObject object) throws JSONException {
        if(object.has("id"))
            id = object.getInt("id");
        if(object.has("name"))
            name = object.getString("name");
        if(object.has("artist"))
            artist = object.getString("artist");
        if(object.has("url"))
            url = object.getString("url");
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
