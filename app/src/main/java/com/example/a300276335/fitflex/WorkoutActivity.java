package com.example.a300276335.fitflex;

/**
 * WorkoutActivity.java shows pdfs of workout plans,
 * users can fiter plans according to their needs using option menus
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationview;
    ////
    private View headerview;
    private ActionBarDrawerToggle drawerToggle;


    ArrayList<Integer> pic = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();

    ArrayList<Integer> pic1 = new ArrayList<>();
    ArrayList<String> title1 = new ArrayList<>();
    ArrayList<String> desc1 = new ArrayList<>();

    ArrayList<Integer> pic2 = new ArrayList<>();
    ArrayList<String> title2 = new ArrayList<>();
    ArrayList<String> desc2 = new ArrayList<>();

    ArrayList<Integer> pic3 = new ArrayList<>();
    ArrayList<String> title3 = new ArrayList<>();
    ArrayList<String> desc3 = new ArrayList<>();

    private boolean muscleFlag = false;
    private boolean fatFlag = false;
    private boolean fitFlag = false;

    protected void addItems(){
        for(int x = 0; x <= 5; x++){
            pic.add(R.drawable.pdf);
        }
        title.add("Muscle Size");
        title.add("Pure Mass");
        title.add("Muscle Growth");
        title.add("Live Fit");
        title.add("Lean Body");
        title.add("Lose Weight");
        desc.add("6 weeks size gain workout program");
        desc.add("8 weeks mass building workout plan");
        desc.add("4 weeks muscle growth");
        desc.add("8 weeks fitness program");
        desc.add("12 weeks weight loss plan");
        desc.add("2-Week training schedule to lose fat");
    }

    protected void showMuscleBuildingPlan(){
        for(int x = 0; x <= 2; x++){
            pic1.add(R.drawable.pdf);
        }
        title1.add("Muscle Size");
        title1.add("Pure Mass");
        title1.add("Muscle Growth");
        desc1.add("6 weeks size gain workout program");
        desc1.add("8 weeks mass building workout plan");
        desc1.add("4 weeks muscle growth");
    }

    protected void showWeightLossPlan(){
        for(int x = 0; x <= 1; x++){
            pic2.add(R.drawable.pdf);
        }
        title2.add("Lean Body");
        title2.add("Lose Weight");
        desc2.add("12 weeks weight loss plan");
        desc2.add("2-Week training schedule to lose fat");
    }

    protected void showGetFitPlan(){
        for(int x = 1; x <= 1 ; x++){
            pic3.add(R.drawable.pdf);
        }
        title3.add("Live Fit");
        desc3.add("8 weeks fitness program");
    }

    Intent intentStart;


    CustomPdfAdapter myAdapter = new CustomPdfAdapter(this, pic,
            title, desc);

    CustomPdfAdapter myAdapter1 = new CustomPdfAdapter(this, pic1,
            title1, desc1);

    CustomPdfAdapter myAdapter2 = new CustomPdfAdapter(this, pic2,
            title2, desc2);

    CustomPdfAdapter myAdapter3 = new CustomPdfAdapter(this, pic3,
            title3, desc3);

    //private boolean isStarted = false; ///////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);



        setupToolbarMenu();
        setupNavigationDrawerMenu();
        setNavigationViewListner();


        addItems();
        showMuscleBuildingPlan();
        showWeightLossPlan();
        showGetFitPlan();


        setSupportActionBar(mToolbar);





            //SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);
            ListView androidListView = (ListView) findViewById(R.id.list_view_pdfs);
            //androidListView.setAdapter(simpleAdapter);
            androidListView.setAdapter(myAdapter);
            androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                            intentStart = new Intent(WorkoutActivity.this,PdfActivity.class);
                            intentStart.putExtra(Intent.EXTRA_TEXT,"muscle_size.pdf");
                            startActivity(intentStart);
                            break;
                        case 1:
                            intentStart = new Intent(WorkoutActivity.this,PdfActivity.class);
                            intentStart.putExtra(Intent.EXTRA_TEXT,"pure_mass.pdf");
                            startActivity(intentStart);
                            break;
                        case 2:
                            intentStart = new Intent(WorkoutActivity.this,PdfActivity.class);
                            intentStart.putExtra(Intent.EXTRA_TEXT,"muscle_growth.pdf");
                            startActivity(intentStart);
                            break;
                        case 3:
                            intentStart = new Intent(WorkoutActivity.this,PdfActivity.class);
                            intentStart.putExtra(Intent.EXTRA_TEXT,"live_fit.pdf");
                            startActivity(intentStart);
                            break;
                        case 4:
                            intentStart = new Intent(WorkoutActivity.this,PdfActivity.class);
                            intentStart.putExtra(Intent.EXTRA_TEXT,"lean_body.pdf");
                            startActivity(intentStart);
                            break;
                        case 5:
                            intentStart = new Intent(WorkoutActivity.this,PdfActivity.class);
                            intentStart.putExtra(Intent.EXTRA_TEXT,"lose_weight.pdf");
                            startActivity(intentStart);
                            break;

                    }
                }
            });

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

        drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState(); //without this hamburger would disappear
    }


    private void setupHeader(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WorkoutActivity.this);
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
                startActivity(new Intent(WorkoutActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        //Toast.makeText(WorkoutActivity.this, itemName + " Clicked",Toast.LENGTH_SHORT).show();


        switch(menuItem.getItemId()){

            case R.id.item_exercises:
                startActivity(new Intent(WorkoutActivity.this, ExerciseActivity.class));
                break;

            case R.id.item_workout:
                startActivity(new Intent(WorkoutActivity.this, WorkoutActivity.class));
                break;

            case R.id.item_nutrition:
                startActivity(new Intent(WorkoutActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_suppliment:
                startActivity(new Intent(WorkoutActivity.this ,NutriSuppsActivity.class).putExtra("go_to", 1));
                break;

            case R.id.item_progress:
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WorkoutActivity.this);
                final String result = prefs.getString("RegisteredUser","null");

                if(result.compareTo("true")!=0){
                    Toast myToast =  Toast.makeText(WorkoutActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    startActivity(new Intent(WorkoutActivity.this, ProgressTrackerActivity.class));
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
                    Toast myToast =  Toast.makeText(WorkoutActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                    myToast.show();
                }
                startActivity(new Intent(WorkoutActivity.this, LoginActivity.class));
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









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //int muscle = R.id.action_muscle;
        //int loss = R.id.action_loss;
        //int fit = R.id.action_fit;



      /*
      * showMuscleBuildingPlan();
      * showWeightLossPlan();
      * showGetFitPlan();
      * */


        // Activate the navigation drawer toggle
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_muscle:
                if(!muscleFlag){
                    muscleFlag = true;
                    fatFlag = false;
                    fitFlag = false;

                    ListView androidListView = (ListView) findViewById(R.id.list_view_pdfs);
                    androidListView.setAdapter(null);
                    //showMuscleBuildingPlan();
                    androidListView.setAdapter(myAdapter1);
                    androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    intentStart = new Intent(WorkoutActivity.this, PdfActivity.class);
                                    intentStart.putExtra(Intent.EXTRA_TEXT, "muscle_size.pdf");
                                    startActivity(intentStart);
                                    break;
                                case 1:
                                    intentStart = new Intent(WorkoutActivity.this, PdfActivity.class);
                                    intentStart.putExtra(Intent.EXTRA_TEXT, "pure_mass.pdf");
                                    startActivity(intentStart);
                                    break;
                                case 2:
                                    intentStart = new Intent(WorkoutActivity.this, PdfActivity.class);
                                    intentStart.putExtra(Intent.EXTRA_TEXT, "muscle_growth.pdf");
                                    startActivity(intentStart);
                                    break;
                            }
                        }
                    });

                    //item.setEnabled(false);
                    //item.setVisible(muscleFlag);

                   // MenuItem fat = (MenuItem)findViewById(R.id.action_loss);
                   // fat.setEnabled(true);
                   // fat.setVisible(!fatFlag);

                   // MenuItem fit = (MenuItem)findViewById(R.id.action_fit);
                   // fit.setEnabled(true);
                   // fit.setVisible(!fitFlag);
                }
                    return true;

            case R.id.action_loss:
                if(!fatFlag) {
                    fatFlag = true;
                    muscleFlag = false;
                    fitFlag = false;


                    ListView myListView = (ListView) findViewById(R.id.list_view_pdfs);
                    myListView.setAdapter(null);
                    //showWeightLossPlan();
                    myListView.setAdapter(myAdapter2);
                    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    intentStart = new Intent(WorkoutActivity.this, PdfActivity.class);
                                    intentStart.putExtra(Intent.EXTRA_TEXT, "lean_body.pdf");
                                    startActivity(intentStart);
                                    break;
                                case 1:
                                    intentStart = new Intent(WorkoutActivity.this, PdfActivity.class);
                                    intentStart.putExtra(Intent.EXTRA_TEXT, "lose_weight.pdf");
                                    startActivity(intentStart);
                                    break;
                            }
                        }
                    });


                }
                    return true;
            case R.id.action_fit:
                if(!fitFlag) {
                    fitFlag = true;
                    fatFlag = false;
                    muscleFlag = false;

                    ListView aListView = (ListView) findViewById(R.id.list_view_pdfs);
                    aListView.setAdapter(null);
                    //showGetFitPlan();
                    aListView.setAdapter(myAdapter3);
                    aListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    intentStart = new Intent(WorkoutActivity.this, PdfActivity.class);
                                    intentStart.putExtra(Intent.EXTRA_TEXT, "live_fit.pdf");
                                    startActivity(intentStart);
                                    break;
                            }
                        }
                    });

                }
                return true;
        }

        return true;
    }

  @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
      super.onPrepareOptionsMenu(menu);

      if (muscleFlag) {
          menu.getItem(0).setEnabled(false);
          menu.getItem(2).setEnabled(true);
          menu.getItem(1).setEnabled(true);
          // You can also use something like:
          // menu.findItem(R.id.example_foobar).setEnabled(false);
      }

      if (fatFlag) {
          menu.getItem(1).setEnabled(false);
          menu.getItem(0).setEnabled(true);
          menu.getItem(2).setEnabled(true);

          // You can also use something like:
          // menu.findItem(R.id.example_foobar).setEnabled(false);
      }

      if (fitFlag) {
          menu.getItem(2).setEnabled(false);
          menu.getItem(1).setEnabled(true);
          menu.getItem(0).setEnabled(true);


          // You can also use something like:
          // menu.findItem(R.id.example_foobar).setEnabled(false);
      }

          //menu.findItem(R.id.action_muscle).setEnabled(true);
      //menu.findItem(R.id.action_loss).setVisible(true);
      //menu.findItem(R.id.action_fit).setVisible(true);


      return true;
  }

}
