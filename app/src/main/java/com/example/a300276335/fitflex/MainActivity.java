package com.example.a300276335.fitflex;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationview;
  ////
    private View headerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbarMenu();
        setupNavigationDrawerMenu();
        setNavigationViewListner();


      //final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
       //final String loggedInUserName = prefs.getString("LoggedInUserName","hello");
       //Toast.makeText(MainActivity.this,loggedInUserName ,Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); //everything will be of same card size

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

/*
        for (int i =0;i<20;i++){
            ListItem listItem = new ListItem(
                    "item " + (i+1),"Description"
            );
            listItems.add(listItem);
        }
*/

        ListItem  listItem1 = new ListItem("Exercises", R.drawable.exercise);
        listItems.add(listItem1);

        ListItem  listItem2 = new ListItem("Workouts", R.drawable.workout);
        listItems.add(listItem2);

        ListItem  listItem3 = new ListItem("Nutritions", R.drawable.nutrition);
        listItems.add(listItem3);

        ListItem  listItem4 = new ListItem("Supplements", R.drawable.supplement);
        listItems.add(listItem4);
        //or use setter and getter instead of constructors




        adapter = new MyAdapter(this,listItems);

        recyclerView.setAdapter(adapter);




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

        ////////////------------- Set Header
        setupHeader();

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState(); //without this hamburger would disappear
    }


    private void setupHeader(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        //Toast.makeText(MainActivity.this, itemName + " Clicked",Toast.LENGTH_SHORT).show();


        switch(menuItem.getItemId()){

            case R.id.item_exercises:
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                break;

            case R.id.item_workout:
                startActivity(new Intent(MainActivity.this, WorkoutActivity.class));
                break;

            case R.id.item_nutrition:
                startActivity(new Intent(MainActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_suppliment:
                startActivity(new Intent(MainActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_progress:
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                final String result = prefs.getString("RegisteredUser","null");

                if(result.compareTo("true")!=0){
                    Toast myToast =  Toast.makeText(MainActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    startActivity(new Intent(MainActivity.this, ProgressTrackerActivity.class));
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
                    Toast myToast =  Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                    myToast.show();
                }
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
