package com.example.a300276335.fitflex;

/**
 *  ExerciseActivity.java shows a list of exercise videos
 *  using intents and passing it to YouTubeActivity
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

public class ExerciseActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationview;
    ////
    private View headerview;


    String[] listviewTitle = new String[]{
            "Ultimate Abs Exercise",
            "Shoulder and Triceps",
            "Calf Exercise",
            "Biceps Exercise",
            "Ultimate Chest Exercise"
    };

    int[] listviewImage = new int[]{
            R.drawable.abs,
            R.drawable.shoulders,
            R.drawable.calf,
            R.drawable.biceps,
            R.drawable.chest
    };

    String[] listviewShortDescription = new String[]{
            "This is the ultimate abs exercise." + "\n" +
                    "Enjoy this vido...",
            "Ultimate shoulder and triceps." + "\n" +
                    "Get ripped...",
            "Get bigger calfs in no time." + "\n" +
                    "Go hard or go home...",
            "Follow this routine for bigger biceps." + "\n" +
                    "No pain, no gain...",
            "Best Exercises for a bigger chest." + "\n" +
                    "Let's Go!"
    };

    Intent intentStart;





    ////--------------Shared preferences













    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        setupToolbarMenu();
        setupNavigationDrawerMenu();
        setNavigationViewListner();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);


        ListView myListView = (ListView) findViewById(R.id.listViewExercises);

        CustomImageAdapter myAdapter = new CustomImageAdapter(this, listviewTitle,
                listviewShortDescription, listviewImage);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        //Abs
                        intentStart = new Intent(ExerciseActivity.this,YouTubeActivity.class);
                        intentStart.putExtra(Intent.EXTRA_TEXT,"5rZd5BSpdBo");
                        startActivity(intentStart);
                        break;
                    case 1:
                        //Shoulder & Triceps
                        intentStart = new Intent(ExerciseActivity.this,YouTubeActivity.class);
                        intentStart.putExtra(Intent.EXTRA_TEXT,"9PsyPp-41q8");
                        startActivity(intentStart);
                        break;
                    case 2:
                        //Calf
                        intentStart = new Intent(ExerciseActivity.this, YouTubeActivity.class);
                        intentStart.putExtra(Intent.EXTRA_TEXT,"fRUikJ__bbg");
                        startActivity(intentStart);
                        break;
                    case 3:
                        //Biceps
                        intentStart = new Intent(ExerciseActivity.this, YouTubeActivity.class);
                        intentStart.putExtra(Intent.EXTRA_TEXT,"O5JTbMMPUUw");
                        startActivity(intentStart);
                        break;
                    case 4:
                        //Chest
                        intentStart = new Intent(ExerciseActivity.this, YouTubeActivity.class);
                        intentStart.putExtra(Intent.EXTRA_TEXT, "ZtlH0A5dlLg");
                        startActivity(intentStart);
                        break;
                }
            }

        });


        //myDrawerLayout = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        //     @Override
        //     public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //         return true;
        //     }
        // });
    }

    private void setupToolbarMenu() {
        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setTitle("FitFlex");
        }


    }

    private void setupNavigationDrawerMenu() {

        navigationview =(NavigationView)findViewById(R.id.navigationView);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        ///
        headerview = navigationview.getHeaderView(0);

        setupHeader();

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState(); //without this hamburger would disappear
    }

    private void setupHeader(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ExerciseActivity.this);
        final String loggedInUserName = prefs.getString("LoggedInUserName","FitFlex");
        final String loggedInUserEmail = prefs.getString("LoggedInUserEmail","Your Personal Trainer");
        TextView username = (TextView)headerview.findViewById(R.id.txtName);
        username.setText(loggedInUserName);
        TextView useremail = (TextView)headerview.findViewById(R.id.txtMotto);
        useremail.setText(loggedInUserEmail);


    }

    private void setNavigationViewListner() {

        navigationview.setNavigationItemSelectedListener(this);
        //////
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        //Toast.makeText(ExerciseActivity.this, itemName + " Clicked",Toast.LENGTH_SHORT).show();


        switch(menuItem.getItemId()){

            case R.id.item_exercises:
                startActivity(new Intent(ExerciseActivity.this, ExerciseActivity.class));
                break;

            case R.id.item_workout:
                startActivity(new Intent(ExerciseActivity.this, WorkoutActivity.class));
                break;

            case R.id.item_nutrition:
                startActivity(new Intent(ExerciseActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_suppliment:
                startActivity(new Intent(ExerciseActivity.this, NutriSuppsActivity.class).putExtra("go_to", 1));
                break;

            case R.id.item_progress:
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ExerciseActivity.this);
                final String result = prefs.getString("RegisteredUser","null");

                if(result.compareTo("true")!=0){
                    Toast myToast =  Toast.makeText(ExerciseActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    startActivity(new Intent(ExerciseActivity.this, ProgressTrackerActivity.class));
                }

                break;

            case  R.id.item_logout:
                /////-----------------call to shared pref to log out
                editSharedPrefs();
                /////logging out from facebook
                try {
                    FacebookSdk.sdkInitialize(getApplicationContext());
                    LoginManager.getInstance().logOut();
                }catch (Exception ex){
                    Toast myToast =  Toast.makeText(ExerciseActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                    myToast.show();
                }
                startActivity(new Intent(ExerciseActivity.this, LoginActivity.class));
                break;

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }

    ////--------------Shared preferences

    private void editSharedPrefs(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("IsLogged", "false");
        editor.putString("RegisteredUser","false");
        editor.putString("UserEmail","null");

        editor.putString("LoggedInUserName","FitFlex");
        editor.putString("LoggedInUserEmail","Your Personal Trainer");

        //editor.putString("BrowseRecsResult", browseRecsOutput.toString());
        editor.commit();
    }

    private void closeDrawer() {

        mDrawerLayout.closeDrawer(Gravity.START);
    }


    private void startDrawer(){
        mDrawerLayout.openDrawer(Gravity.START);
    }


    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(Gravity.START))
            closeDrawer();
        else
            super.onBackPressed();
    }




}
