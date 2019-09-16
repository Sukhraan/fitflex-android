package com.example.a300276335.fitflex;
/**
 * SplashActivity.java -- shows slideshow of images using Splash Screen
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    String APP_TAG = "FitFlex";
    ImageView imgView;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String result = prefs.getString("IsLogged","null");


        imgView = (ImageView) findViewById(R.id.imgView);
        try {
            TimerTask myTask = new TimerTask() {
                @Override
                public void run() {
                    finish();
                    if(result.compareTo("true")==0){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }
            };

            Timer opening = new Timer();
            opening.schedule(myTask, 5500);

            BitmapDrawable frame1 = (BitmapDrawable) getResources().getDrawable(
                    R.drawable.logo);
            BitmapDrawable frame2 = (BitmapDrawable) getResources().getDrawable(
                    R.drawable.first);
            BitmapDrawable frame3 = (BitmapDrawable) getResources().getDrawable(
                    R.drawable.second);

            anim = new AnimationDrawable();
            anim.addFrame(frame1, 2000);
            anim.addFrame(frame2, 2000);
            anim.addFrame(frame3, 2000);

            imgView.setBackgroundDrawable(anim);
            anim.start();

        } catch (Exception ex) {
            Log.e(APP_TAG,ex.getMessage());
        }
    }
}
