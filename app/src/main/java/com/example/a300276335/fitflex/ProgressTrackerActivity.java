package com.example.a300276335.fitflex;
/**
 * ProgressTrackerActivity.java -- user set goals and keep track of weekly
 * progress
 */

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

public class ProgressTrackerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    SQLiteDatabase db;
    StringBuffer modifyRecsOutput = new StringBuffer();
    StringBuffer browseRecsOutput = new StringBuffer();

    String UserId;

    TextView activityTitle;
    EditText myHeight, myWeight, myChest, myArms, myLegs, myCalves, myWaist;
    Button myButton, cancelButton;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationview;
    ////
    private View headerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_tracker);

        setupToolbarMenu();
        setupNavigationDrawerMenu();
        setNavigationViewListner();

        activityTitle = (TextView)findViewById(R.id.textViewTitle);
        myHeight = (EditText)findViewById(R.id.editTextHeight);
        myWeight = (EditText)findViewById(R.id.editTextWeight);
        myChest = (EditText)findViewById(R.id.editTextChest);
        myArms = (EditText)findViewById(R.id.editTextArms);
        myLegs = (EditText)findViewById(R.id.editTextLegs);
        myCalves = (EditText)findViewById(R.id.editTextCalves);
        myWaist = (EditText)findViewById(R.id.editTextWaist);
        myButton = (Button)findViewById(R.id.buttonProgress);
        cancelButton = (Button)findViewById(R.id.buttonCancelTracker);
        createDB();
        createTables();

        getUserId();
        initialize();





       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);


//##################################################debuging code###################################
        //storeSharedPrefs();
        //startActivity(new Intent(ProgressTrackerActivity.this, ProgressResultActivity.class));
//##################################################################################################

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHeight.setText("");
                myWeight.setText("");
                myChest.setText("");
                myArms.setText("");
                myLegs.setText("");
                myCalves.setText("");
                myWaist.setText("");
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myButton.getText().equals("Update Progress")) {
                    //redirect to update and show progress



                    //do DATA VALIDATION FIRST
                    //PASS ARG after DOING DOUBLE.PARSEDOUBLE
                    //////////***********


                    if (myHeight.getText().toString().length() == 0 || myWeight.getText().toString().length() == 0 || myChest.getText().toString().length() == 0 || myArms.getText().toString().length() == 0 || myLegs.getText().toString().length() == 0 || myCalves.getText().toString().length() == 0 || myWaist.toString().length() == 0) {

                        // myHeight.setText("");
                        // myWeight.setText("");
                        // myChest.setText("");
                        // myArms.setText("");
                        //myLegs.setText("");
                        //myCalves.setText("");
                        //myWaist.setText("");
                        Toast myToast =  Toast.makeText(ProgressTrackerActivity.this, "Field can't be empty", Toast.LENGTH_SHORT);
                        myToast.show();
                    }else
                    {
                        insertProgress(UserId,Double.parseDouble(myHeight.getText().toString()),Double.parseDouble(myWeight.getText().toString()),Double.parseDouble(myChest.getText().toString()),Double.parseDouble(myArms.getText().toString()),Double.parseDouble(myLegs.getText().toString()),Double.parseDouble(myCalves.getText().toString()),Double.parseDouble(myWaist.getText().toString()));
                        storeSharedPrefs();
                        startActivity(new Intent(ProgressTrackerActivity.this, ProgressReportActivity.class));
                    }



                    /////////***********

                    //###########Debug

                    //browseTable("progress");
                    //storeSharedPrefs();
/*
                    startActivity(new Intent(ProgressTrackerActivity.this, ProgressResultActivity.class));
*/

                    //startActivity(new Intent(ProgressTrackerActivity.this, ProgressTrackerActivity.class));

                }else if(myButton.getText().equals("Set Goals"))
                {
                    //New Goals
                    //do DATA VALIDATION FIRST
                    //PASS ARG after DOING DOUBLE.PARSEDOUBLE

                    if(myHeight.getText().toString().length() == 0 || myWeight.getText().toString().length() == 0 || myChest.getText().toString().length() == 0 || myArms.getText().toString().length() == 0 || myLegs.getText().toString().length() == 0 || myCalves.getText().toString().length() == 0 || myWaist.toString().length() == 0) {

                        //myHeight.setText("");
                        //myWeight.setText("");
                        //myChest.setText("");
                        //myArms.setText("");
                        //myLegs.setText("");
                        //myCalves.setText("");
                        // myWaist.setText("");
                        Toast myToast =  Toast.makeText(ProgressTrackerActivity.this, "Field can't be empty", Toast.LENGTH_SHORT);
                        myToast.show();
                    }else
                    {
                        setOrUpdateUserGoals(UserId,Double.parseDouble(myHeight.getText().toString()),Double.parseDouble(myWeight.getText().toString()),Double.parseDouble(myChest.getText().toString()),Double.parseDouble(myArms.getText().toString()),Double.parseDouble(myLegs.getText().toString()),Double.parseDouble(myCalves.getText().toString()),Double.parseDouble(myWaist.getText().toString()));
                        Toast myToast =  Toast.makeText(ProgressTrackerActivity.this, "Goals updated", Toast.LENGTH_SHORT);
                        myToast.show();
                        //storeSharedPrefs();
                        //startActivity(new Intent(ProgressTrackerActivity.this, ProgressReportActivity.class));

                    }

                    //setOrUpdateUserGoals(UserId,5.8, 75.0,42.0,14.0,23.0,16.0,30.0 );
                    //execute set new goal query and start itself again, as now there are goals for user layout resets itself
                    //##############testing#############
                    //browseTable("goals");


                    //storeSharedPrefs();
                    //startActivity(new Intent(ProgressTrackerActivity.this, ProgressReportActivity.class));
                    //##################################
                    //startActivity(new Intent(ProgressTrackerActivity.this, ProgressTrackerActivity.class));
                }

            }
        });



    }

    private void createDB(){
        try{
            //Mode private on this instance can access the database, factory:: null use default cursor factory settings
            db = openOrCreateDatabase("UserManager_db", MODE_PRIVATE, null);
            modifyRecsOutput.append("Opened or " + "created test_db database...\n");
        }catch(Exception ex){
            modifyRecsOutput.append(ex.getMessage() + "\n");
        }
    }

    private void createTables(){

        //Exec Sql; nothing returned e.g DROP TABLE, both use QUery String
        //Raw Query: if it returned cursor e.g SELECT QUERIES, use Quey STring
        //SQLite direct: CRUD queries i.e CREATE etc.
        try{

            String createProgressTable = "CREATE TABLE IF NOT EXISTS progress " + "(id integer primary key autoincrement, user_id text, week integer, height real,weight real, chest real, arms real, legs real, calves real, waist real,  FOREIGN KEY (user_id) REFERENCES user(user_id) );";
            db.execSQL(createProgressTable);
            modifyRecsOutput.append("Created progress table ...\n");

            String createGoalsTable = "CREATE TABLE IF NOT EXISTS goals " + "(id integer primary key autoincrement, user_id text, height real, weight real,  chest real, arms real, legs real, calves real, waist real,  FOREIGN KEY (user_id) REFERENCES user(user_id) );";
            db.execSQL(createGoalsTable);
            modifyRecsOutput.append("Created goals table ...\n");

        }catch(Exception ex){
            modifyRecsOutput.append(ex.getMessage() + "\n");
        }
    }
    private void browseTable(String table){
        String browseTab = "SELECT * FROM " + table +" ;";
        try{
            Cursor cursor = db.rawQuery(browseTab, null);
            browseRecsOutput.append("Displaying "+ table +" table...\n");
            if(cursor != null){
                cursor.moveToFirst();
                do{
                    String eachRec = cursor.getString(0) + " " +
                            cursor.getString(1) + " " +
                            cursor.getString(2) + "\n";
                    browseRecsOutput.append(eachRec);
                }while(cursor.moveToNext());
            }
        }catch (Exception ex){
            browseRecsOutput.append(ex.getMessage() + "\n");
        }
    }


    private void getUserId(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressTrackerActivity.this);
        //###################################################still have to change 'sukh@gmail.com' to dynamic binding of pref.getString('UserEmail',"')

        String getUserid = "SELECT user_id from user where user_email = '"+ prefs.getString("UserEmail", "")   + "' Group By user_id ;";
        try{
            Cursor cursor = db.rawQuery(getUserid, null);
            browseRecsOutput.append("Getting user id...\n");
            if(cursor != null){
                cursor.moveToFirst();
                do{
                    UserId = cursor.getString(0);
                    browseRecsOutput.append(UserId + "\n");
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception ex){
            browseRecsOutput.append(ex.getMessage() + "\n");
        }


    }


    private void setOrUpdateUserGoals(String id, Double height, Double weight ,Double chest, Double arms, Double legs,Double calves, Double waist ){
        ContentValues userCV = new ContentValues();

        userCV.put("user_id",id);
        userCV.put("height",height);
        userCV.put("weight",weight);
        userCV.put("chest",chest);
        userCV.put("arms",arms);
        userCV.put("legs",legs);
        userCV.put("calves",calves);
        userCV.put("waist",waist);
        try{
            db.replace("goals",null,userCV);
            //db.update only updates if exsist
            modifyRecsOutput.append("replaced goals record with id...");

        }catch(Exception ex)
        {
            modifyRecsOutput.append(ex.getMessage()+"\n");
        }

    }

    private void insertProgress(String id, Double height, Double weight ,Double chest, Double arms, Double legs,Double calves, Double waist ){
        ContentValues userCV = new ContentValues();
        int newWeek;
        try{
            newWeek = getNextWeek(id);
            userCV.put("user_id",id);
            userCV.put("week",newWeek);
            userCV.put("height",height);
            userCV.put("weight",weight);
            userCV.put("chest",chest);
            userCV.put("arms",arms);
            userCV.put("legs",legs);
            userCV.put("calves",calves);
            userCV.put("waist",waist);


            //get next week for userID


            db.insert("progress",null,userCV);
            //db.update only updates if exist
            modifyRecsOutput.append("insert progress record for id..." + id + " for week " + newWeek );

        }catch(Exception ex)
        {
            modifyRecsOutput.append(ex.getMessage()+"\n");
        }

    }

    private int getNextWeek(String id){
        int prevWeek = 0 , nextWeek;
        String getPrevWeek = "SELECT max(week) from progress where user_id = "  + id ;
        try{
            Cursor cursor = db.rawQuery(getPrevWeek, null);
            browseRecsOutput.append("Getting user id...\n");
            if(cursor != null){
                cursor.moveToFirst();
                do{
                    prevWeek = Integer.parseInt(cursor.getString(0));
                    browseRecsOutput.append(prevWeek + "\n");
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception ex){
            browseRecsOutput.append(ex.getMessage() + "\n");
        }

        nextWeek = prevWeek + 1;
        return nextWeek;

    }

/*
    private void replaceStudent(String id, String name,String major){
        ContentValues studentCV = new ContentValues();
        studentCV.put("id",id);
        studentCV.put("user_id",name);
        studentCV.put("major",major);
        try{
            db.replace("students",null,studentCV);
            //db.update only updates if exsist
            modifyRecsOutput.append("replaced student record with id...");

        }catch(Exception ex)
        {
            modifyRecsOutput.append(ex.getMessage()+"\n");
        }

    }*/

    private void initialize(){
        String browseGoals = "SELECT * FROM goals where user_id = " + UserId;
        try{
            Cursor cursor = db.rawQuery(browseGoals, null);
            browseRecsOutput.append("Checking goals record...and deciding on structure\n");
            //if(cursor != null)
            if(cursor.getCount() != 0){
                cursor.moveToFirst();

                activityTitle.setText("Week's Progress");
                myHeight.setText("");
                myWeight.setText("");
                myChest.setText("");
                myArms.setText("");
                myLegs.setText("");
                myCalves.setText("");
                myWaist.setText("");
                myButton.setText("Update Progress");
                do{

                    String eachStudRec = cursor.getString(0) + " " +
                            cursor.getString(1) + " " +
                            cursor.getString(2) + "\n";
                    browseRecsOutput.append(eachStudRec);

                }while(cursor.moveToNext());

            }
            else
            {
                activityTitle.setText("New Goals");
                myHeight.setText("");
                myWeight.setText("");
                myChest.setText("");
                myArms.setText("");
                myLegs.setText("");
                myCalves.setText("");
                myWaist.setText("");
                myButton.setText("Set Goals");

            }

            cursor.close();
        }catch (Exception ex){
            browseRecsOutput.append(ex.getMessage() + "\n");
        }

    }





    private void storeSharedPrefs(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressTrackerActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ModifyRecsResult", modifyRecsOutput.toString());
        editor.putString("BrowseRecsResult", browseRecsOutput.toString());
        editor.commit();
    }




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
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressTrackerActivity.this);
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
                startActivity(new Intent(ProgressTrackerActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        //Toast.makeText(ProgressTrackerActivity.this, itemName + " Clicked",Toast.LENGTH_SHORT).show();


        switch(menuItem.getItemId()){

            case R.id.item_exercises:
                startActivity(new Intent(ProgressTrackerActivity.this, ExerciseActivity.class));
                break;

            case R.id.item_workout:
                startActivity(new Intent(ProgressTrackerActivity.this, WorkoutActivity.class));
                break;

            case R.id.item_nutrition:
                startActivity(new Intent(ProgressTrackerActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_suppliment:
                startActivity(new Intent(ProgressTrackerActivity.this, NutriSuppsActivity.class).putExtra("go_to", 1));
                break;

            case R.id.item_progress:
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressTrackerActivity.this);
                final String result = prefs.getString("RegisteredUser","null");

                if(result.compareTo("true")!=0){
                    Toast myToast =  Toast.makeText(ProgressTrackerActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    startActivity(new Intent(ProgressTrackerActivity.this, ProgressTrackerActivity.class));
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
                    Toast myToast =  Toast.makeText(ProgressTrackerActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                    myToast.show();
                }
                startActivity(new Intent(ProgressTrackerActivity.this, LoginActivity.class));
                break;

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;


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
