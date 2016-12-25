package com.altruismradio.activities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.altruismradio.App;
import com.altruismradio.R;
import com.altruismradio.activities.views.SlideshowView;
import com.altruismradio.api.Api;
import com.altruismradio.api.ApiException;
import com.altruismradio.api.ApiRequestListener;
import com.altruismradio.api.objects.Audio;
import com.altruismradio.api.objects.Station;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by yuriy on 22.12.16.
 */
public class PlayerActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private FloatingActionButton fab;
    private SlidingUpPanelLayout slidingLayout;
    private RelativeLayout dragView;
    private RelativeLayout mainView;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        handler = new Handler();
        Api.getStation(new ApiRequestListener() {
            @Override
            public void onSuccess(Object object) {
                try {
                    URL url = new URL(((Station)object).imgs[4]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    ((SlideshowView)findViewById(R.id.slideshow)).setBitmap(BitmapFactory.decodeStream(input));
                    ((SlideshowView)findViewById(R.id.slideshow)).invalidate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(ApiException exception) {

            }
        },17);
        //set layout slide listener
        slidingLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        dragView = (RelativeLayout) findViewById(R.id.dragView);
        mainView = (RelativeLayout) findViewById(R.id.mainView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mainView.post(new Runnable() {
            @Override
            public void run() {
                slidingLayout.setAnchorPoint(mainView,dragView,65);
                if(slidingLayout.getPanelState()== SlidingUpPanelLayout.PanelState.ANCHORED){
                    fab.setTranslationY(- getResources().getDimension(R.dimen.controls_height));
                }
            }
        });

        slidingLayout.addPanelSlideListener(onSlideListener());
        slidingLayout.setShadowHeight(10);
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
        initToolbar();
        initNavigationView();
    }

    private View.OnClickListener onShowListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show sliding layout in bottom of screen (not expand it)
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        };
    }

    /**
     * Hide sliding layout when click button
     * @return
     */
    private View.OnClickListener onHideListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide sliding layout
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        };
    }

    private SlidingUpPanelLayout.PanelSlideListener onSlideListener() {
        return new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if(newState== SlidingUpPanelLayout.PanelState.EXPANDED)
                    slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

                if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED && previousState == SlidingUpPanelLayout.PanelState.DRAGGING){
                    fab.setTranslationY(0);
                    fab.animate().scaleX(1).scaleY(1).setDuration(160).setInterpolator(new AccelerateDecelerateInterpolator());
                }
                if(newState == SlidingUpPanelLayout.PanelState.ANCHORED && previousState == SlidingUpPanelLayout.PanelState.DRAGGING){
                    fab.setTranslationY(- getResources().getDimension(R.dimen.controls_height));
                    fab.animate().scaleX(1).scaleY(1).setDuration(160).setInterpolator(new AccelerateDecelerateInterpolator());
                }
                if(newState == SlidingUpPanelLayout.PanelState.DRAGGING){
                    fab.animate().scaleX(0).scaleY(0).setDuration(160).setInterpolator(new AccelerateDecelerateInterpolator());
                }
            }
        };
    }
    private View.OnTouchListener getSliderTouchListener(){
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("d",""+(dragView.getY())+" "+(mainView.getHeight()-slidingLayout.getPanelHeight()));
                return dragView.getY()+10<+mainView.getHeight()-slidingLayout.getPanelHeight();
            }
        };
    }






    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
     //   toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
     /*   navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionNotificationItem:
                        showNotificationTab();
                }
                return true;
            }
        });*/
    }
}
