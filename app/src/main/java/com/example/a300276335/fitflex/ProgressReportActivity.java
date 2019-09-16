package com.example.a300276335.fitflex;

/**
 * ProgressReportActivity.java displays users report in a tabular form
 * User can set new goals
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProgressReportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    SQLiteDatabase db;
    TableLayout t1;
    String UserId;
    StringBuffer modifyRecsOutput = new StringBuffer();
    StringBuffer browseRecsOutput = new StringBuffer();
    NumberFormat formatter = new DecimalFormat("#0.00");
    Button setGoals;


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationview;
    ////
    private View headerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);



        setupToolbarMenu();
        setupNavigationDrawerMenu();
        setNavigationViewListner();

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);


        setGoals = (Button) findViewById(R.id.setGoalsButton);

        setGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGoals(UserId);
                startActivity(new Intent(ProgressReportActivity.this, ProgressTrackerActivity.class));

            }
        });


        TableLayout tl = (TableLayout) findViewById(R.id.main_table);
        TableRow tr_head = new TableRow(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tr_head.setId(View.generateViewId());
        }else{
            tr_head.setId(Utils.generateViewId());
        }
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));



        TextView label_week = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_week.setId(View.generateViewId());
        }else{
            label_week.setId(Utils.generateViewId());
        }
        label_week.setText("Week"); // set the text for the header
        label_week.setTextColor(Color.WHITE); // set the color
        label_week.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_week); // add the column to the table row here



        TextView label_height = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_height.setId(View.generateViewId());
        }else{
            label_height.setId(Utils.generateViewId());
        }

        label_height.setText("Ht(Ft.)");
        label_height.setTextColor(Color.WHITE);
        label_height.setPadding(5, 5, 5, 5);
        tr_head.addView(label_height);// add the column to the table row here

        TextView label_weight_kg = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_weight_kg.setId(View.generateViewId());
        }else{
            label_weight_kg.setId(Utils.generateViewId());
        }
        label_weight_kg.setText("Wt(Kg.)"); // set the text for the header
        label_weight_kg.setTextColor(Color.WHITE); // set the color
        label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg); // add the column to the table row here
        ////////////////////////

        TextView label_chest = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_chest.setId(View.generateViewId());
        }else{
            label_chest.setId(Utils.generateViewId());
        }

        label_chest.setText("Chest(in.)");
        label_chest.setTextColor(Color.WHITE);
        label_chest.setPadding(5, 5, 5, 5);
        tr_head.addView(label_chest);// add the column to the table row here

        TextView label_arms = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_arms.setId(View.generateViewId());
        }else{
            label_arms.setId(Utils.generateViewId());
        }
        label_arms.setText("Arms(in.)"); // set the text for the header
        label_arms.setTextColor(Color.WHITE); // set the color
        label_arms.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_arms); // add the column to the table row here


        //////

        TextView label_legs = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_legs.setId(View.generateViewId());
        }else{
            label_legs.setId(Utils.generateViewId());
        }

        label_legs.setText("Legs(in.)");
        label_legs.setTextColor(Color.WHITE);
        label_legs.setPadding(5, 5, 5, 5);
        tr_head.addView(label_legs);// add the column to the table row here

        TextView label_calves = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_calves.setId(View.generateViewId());
        }else{
            label_calves.setId(Utils.generateViewId());
        }
        label_calves.setText("Calves(in.)"); // set the text for the header
        label_calves.setTextColor(Color.WHITE); // set the color
        label_calves.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_calves); // add the column to the table row here

        ////////////

        TextView label_waist = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            label_waist.setId(View.generateViewId());
        }else{
            label_waist.setId(Utils.generateViewId());
        }

        label_waist.setText("Waist(in.)");
        label_waist.setTextColor(Color.WHITE);
        label_waist.setPadding(5, 5, 5, 5);
        tr_head.addView(label_waist);// add the column to the table row here


        ////////



        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));





        createDB();

        getUserId();


        try{
            String browseTab = "SELECT * FROM " + "progress" +" where user_id = "  + UserId + " order by week asc;" ;
            Integer count=0;
            Cursor cursor = db.rawQuery(browseTab, null);
            browseRecsOutput.append("Displaying "+ "progress" +" table...\n");
            if(cursor != null) {
                cursor.moveToFirst();
                do {

                  /*
                    String eachRec = cursor.getString(0) + " " +
                            cursor.getString(1) + " " +
                            cursor.getString(2) + "\n";
                    browseRecsOutput.append(eachRec);
                */
                    String week = cursor.getString(2);
                    String height_ft = formatter.format(cursor.getDouble(3));// get the first variable
                    String weight_kg = formatter.format(cursor.getDouble(4));// get the second variable
// Create the table row
                    String chest = formatter.format(cursor.getDouble(5));
                    String arms = formatter.format(cursor.getDouble(6));
                    String legs = formatter.format(cursor.getDouble(7));
                    String calves = formatter.format(cursor.getDouble(8));
                    String waist = formatter.format(cursor.getDouble(9));

                    TableRow tr = new TableRow(this);
                    if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);

                    tr.setId(100 + count);

                    tr.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));


                    TextView labelWEEK = new TextView(this);
                    labelWEEK.setId(200 + count);
                    labelWEEK.setText(week);
                    labelWEEK.setPadding(2, 0, 5, 0);
                    labelWEEK.setTextColor(Color.WHITE);
                    tr.addView(labelWEEK);

//Create two columns to add as table data
                    // Create a TextView to add date
                    TextView labelHEIGHT = new TextView(this);
                    labelHEIGHT.setId(200 + count);
                    labelHEIGHT.setText(height_ft);
                    labelHEIGHT.setPadding(2, 0, 5, 0);
                    labelHEIGHT.setTextColor(Color.WHITE);
                    tr.addView(labelHEIGHT);

                    TextView labelWEIGHT = new TextView(this);
                    labelWEIGHT.setId(200 + count);
                    labelWEIGHT.setText(weight_kg);
                    labelWEIGHT.setPadding(2, 0, 5, 0);
                    labelWEIGHT.setTextColor(Color.WHITE);
                    tr.addView(labelWEIGHT);
//////////////////
                    TextView labelCHEST = new TextView(this);
                    labelCHEST.setId(200 + count);
                    labelCHEST.setText(chest);
                    labelCHEST.setPadding(2, 0, 5, 0);
                    labelCHEST.setTextColor(Color.WHITE);
                    tr.addView(labelCHEST);

                    TextView labelARMS = new TextView(this);
                    labelARMS.setId(200 + count);
                    labelARMS.setText(arms);
                    labelARMS.setPadding(2, 0, 5, 0);
                    labelARMS.setTextColor(Color.WHITE);
                    tr.addView(labelARMS);
///////////
                    TextView labelLEGS = new TextView(this);
                    labelLEGS.setId(200 + count);
                    labelLEGS.setText(legs);
                    labelLEGS.setPadding(2, 0, 5, 0);
                    labelLEGS.setTextColor(Color.WHITE);
                    tr.addView(labelLEGS);

                    TextView labelCALVES = new TextView(this);
                    labelCALVES.setId(200 + count);
                    labelCALVES.setText(calves);
                    labelCALVES.setPadding(2, 0, 5, 0);
                    labelCALVES.setTextColor(Color.WHITE);
                    tr.addView(labelCALVES);

                    //////////////

                    TextView labelWAIST = new TextView(this);
                    labelWAIST.setId(200 + count);
                    labelWAIST.setText(waist);
                    labelWAIST.setTextColor(Color.WHITE);
                    tr.addView(labelWAIST);


// finally add this to the table row
                    tl.addView(tr, new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.FILL_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    count++;


                } while (cursor.moveToNext());

                cursor.close();
            }
            ///// adding Goals record

            String browseGoalsTab = "SELECT * FROM " + "goals" +" where user_id = "  + UserId + " ;" ;
            Cursor cursor1 = db.rawQuery(browseGoalsTab, null);
            browseRecsOutput.append("Displaying "+ "goals" +" table...\n");
            if(cursor1 != null) {
                cursor1.moveToFirst();


                  /*
                    String eachRec = cursor.getString(0) + " " +
                            cursor.getString(1) + " " +
                            cursor.getString(2) + "\n";
                    browseRecsOutput.append(eachRec);
                */
                String week = "GOALS";
                String height_ft = formatter.format(cursor1.getDouble(2));// get the first variable
                String weight_kg = formatter.format(cursor1.getDouble(3));// get the second variable
// Create the table row
                String chest = formatter.format(cursor1.getDouble(4));
                String arms = formatter.format(cursor1.getDouble(5));
                String legs = formatter.format(cursor1.getDouble(6));
                String calves = formatter.format(cursor1.getDouble(7));
                String waist = formatter.format(cursor1.getDouble(8));

                TableRow tr = new TableRow(this);
                tr.setBackgroundColor(Color.RED);

                tr.setId(100 + count);

                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));


                TextView labelWEEK = new TextView(this);
                labelWEEK.setId(200 + count);
                labelWEEK.setText(week);
                labelWEEK.setPadding(2, 0, 5, 0);
                labelWEEK.setTextColor(Color.WHITE);
                tr.addView(labelWEEK);

//Create two columns to add as table data
                // Create a TextView to add date
                TextView labelHEIGHT = new TextView(this);
                labelHEIGHT.setId(200 + count);
                labelHEIGHT.setText(height_ft);
                labelHEIGHT.setPadding(2, 0, 5, 0);
                labelHEIGHT.setTextColor(Color.WHITE);
                tr.addView(labelHEIGHT);

                TextView labelWEIGHT = new TextView(this);
                labelWEIGHT.setId(200 + count);
                labelWEIGHT.setText(weight_kg);
                labelWEIGHT.setPadding(2, 0, 5, 0);
                labelWEIGHT.setTextColor(Color.WHITE);
                tr.addView(labelWEIGHT);
//////////////////
                TextView labelCHEST = new TextView(this);
                labelCHEST.setId(200 + count);
                labelCHEST.setText(chest);
                labelCHEST.setPadding(2, 0, 5, 0);
                labelCHEST.setTextColor(Color.WHITE);
                tr.addView(labelCHEST);

                TextView labelARMS = new TextView(this);
                labelARMS.setId(200 + count);
                labelARMS.setText(arms);
                labelARMS.setPadding(2, 0, 5, 0);
                labelARMS.setTextColor(Color.WHITE);
                tr.addView(labelARMS);
///////////
                TextView labelLEGS = new TextView(this);
                labelLEGS.setId(200 + count);
                labelLEGS.setText(legs);
                labelLEGS.setPadding(2, 0, 5, 0);
                labelLEGS.setTextColor(Color.WHITE);
                tr.addView(labelLEGS);

                TextView labelCALVES = new TextView(this);
                labelCALVES.setId(200 + count);
                labelCALVES.setText(calves);
                labelCALVES.setPadding(2, 0, 5, 0);
                labelCALVES.setTextColor(Color.WHITE);
                tr.addView(labelCALVES);

                //////////////

                TextView labelWAIST = new TextView(this);
                labelWAIST.setId(200 + count);
                labelWAIST.setText(waist);
                labelWAIST.setTextColor(Color.WHITE);
                tr.addView(labelWAIST);


// finally add this to the table row
                tl.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                count++;





                cursor1.close();

            }



        }catch (Exception ex){
            browseRecsOutput.append(ex.getMessage() + "\n");
        }

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

    private void getUserId(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressReportActivity.this);
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

    private void deleteGoals(String id){
        String deleteQuery = "DELETE FROM goals WHERE user_id = " + id;
        //String deleteQuery = "DELETE FROM students WHERE id = ?;";
        try{
            //db.execSQL(deleteQuery,new Object[]{id});
            db.execSQL(deleteQuery);
            modifyRecsOutput.append("Deleted goals record with id "+ id + "\n");
        }
        catch(Exception ex){modifyRecsOutput.append(ex.getMessage()+"\n");}
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
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressReportActivity.this);
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
                startActivity(new Intent(ProgressReportActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        //Toast.makeText(ProgressReportActivity.this, itemName + " Clicked",Toast.LENGTH_SHORT).show();


        switch(menuItem.getItemId()){

            case R.id.item_exercises:
                startActivity(new Intent(ProgressReportActivity.this, ExerciseActivity.class));
                break;

            case R.id.item_workout:
                startActivity(new Intent(ProgressReportActivity.this, WorkoutActivity.class));
                break;

            case R.id.item_nutrition:
                startActivity(new Intent(ProgressReportActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_suppliment:
                startActivity(new Intent(ProgressReportActivity.this, NutriSuppsActivity.class).putExtra("go_to", 1));
                break;

            case R.id.item_progress:
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProgressReportActivity.this);
                final String result = prefs.getString("RegisteredUser","null");

                if(result.compareTo("true")!=0){
                    Toast myToast =  Toast.makeText(ProgressReportActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    startActivity(new Intent(ProgressReportActivity.this, ProgressTrackerActivity.class));
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
                    Toast myToast =  Toast.makeText(ProgressReportActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                    myToast.show();
                }
                startActivity(new Intent(ProgressReportActivity.this, LoginActivity.class));
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
