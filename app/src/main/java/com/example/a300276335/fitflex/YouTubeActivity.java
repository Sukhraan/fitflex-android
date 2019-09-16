package com.example.a300276335.fitflex;

/**
 * YouTubeActivity.java - starts YouTubePlayer with our app
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private String textEntered;
    private CoordinatorLayout rootLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        //fab.setVisibility(View.VISIBLE);


        youTubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        Intent intentThatStartedAct = getIntent();
        if(intentThatStartedAct.hasExtra(Intent.EXTRA_TEXT)){
            textEntered = intentThatStartedAct.getStringExtra(Intent.EXTRA_TEXT);
        }

        rootLayout = (CoordinatorLayout)findViewById(R.id.myLayout);

        fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textEntered);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share via"));

                //Snackbar.make(rootLayout,"Link Shared",Snackbar.LENGTH_LONG).show();
            }
        });




    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

            fab.setVisibility(savedInstanceState.getInt("fabVisibilty"));

        // Read values from the "savedInstanceState"-object and put them in your textview
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need for FAB into "outState"-object
        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
        outState.putInt("fabVisibilty",View.INVISIBLE);
        }
        else{
            outState.putInt("fabVisibilty",View.VISIBLE);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                        boolean wasRestored) {
        if(!wasRestored){
            youTubePlayer.cueVideo(textEntered);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult
            initError) {
        if (initError.isUserRecoverableError()) {
            initError.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), initError.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
