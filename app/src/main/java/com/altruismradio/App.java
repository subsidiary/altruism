package com.altruismradio;

import android.app.Application;
import android.content.Context;

import com.altruismradio.api.Api;
import com.altruismradio.api.ApiException;
import com.altruismradio.api.ApiRequestListener;
import com.altruismradio.api.objects.Audio;
import com.altruismradio.api.objects.Station;

import java.util.List;

public class App extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

/*        Api.getAudio(new ApiRequestListener() {
            @Override
            public void onSuccess(Object object) {
                Audio audio = (Audio) object;
                System.out.println("----------------------------------------------------------");
                System.out.println("Got info about audio with id "+audio.id+":");
                System.out.println("Id: " + audio.id);
                System.out.println("Name: " + audio.name);
                System.out.println("Artist: " + audio.artist);
                System.out.println("Imgs:");
                if(audio.imgs!=null)
                    for (int i=0;i<audio.imgs.length;i++){
                        System.out.println((i+1)+" - "+audio.imgs[i]);
                    }
                System.out.println("Url: " + audio.url);
            }

            @Override
            public void onFailure(ApiException exception) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Error message: "+exception.message);
                System.out.println("Error code: "+exception.code);
            }
        },1793);


        Api.getStations(new ApiRequestListener() {
            @Override
            public void onSuccess(Object object) {
                List<Station> stations = (List<Station>) object;
                System.out.println("----------------------------------------------------------");
                System.out.println("Got names of the stations :");
                for(int i=0;i<stations.size();i++){
                    System.out.println((i+1)+" - "+stations.get(i).name+" ("+stations.get(i).id+")");
                }
            }

            @Override
            public void onFailure(ApiException exception) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Error message: "+exception.message);
                System.out.println("Error code: "+exception.code);
            }
        },"id,name");


        Api.getStation(new ApiRequestListener() {
            @Override
            public void onSuccess(Object object) {
                Station station = (Station) object;
                System.out.println("----------------------------------------------------------");
                System.out.println("Got info about the station with id "+station.id+":");
                System.out.println("Id: " + station.id);
                System.out.println("Name: " + station.name);
                System.out.println("Imgs:");
                if(station.imgs!=null)
                    for (int i=0;i<station.imgs.length;i++){
                        System.out.println((i+1)+" - "+station.imgs[i]);
                    }
                System.out.println("Audios:");
                if(station.audios!=null)
                    for (int i=0;i<station.audios.length;i++){
                        System.out.println((i+1)+" - "+station.audios[i].name+" "+station.audios[i].url);
                    }
            }

            @Override
            public void onFailure(ApiException exception) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Error message: "+exception.message);
                System.out.println("Error code: "+exception.code);
            }
        },16);



        Api.sendEmail(new ApiRequestListener() {
            @Override
            public void onSuccess(Object object) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Yes!!!!");
            }
            @Override
            public void onFailure(ApiException exception) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Error message: "+exception.message);
                System.out.println("Error code: "+exception.code);
            }
        },"This method sends an email to me","With someones feedback about app, so if you run this code, i'll get the email");*/
    }
}
