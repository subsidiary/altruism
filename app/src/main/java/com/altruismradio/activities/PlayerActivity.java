package com.altruismradio.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.altruismradio.App;
import com.altruismradio.R;
import com.altruismradio.api.Api;
import com.altruismradio.api.ApiException;
import com.altruismradio.api.ApiRequestListener;
import com.altruismradio.api.objects.Audio;

import java.io.IOException;

/**
 * Created by yuriy on 22.12.16.
 */
public class PlayerActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
    }
}
