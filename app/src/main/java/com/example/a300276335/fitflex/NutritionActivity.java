package com.example.a300276335.fitflex;
/**
 * NutritionActivity.java displays diet information
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

public class NutritionActivity extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString = {
            "Proteins", "Carbohydrates", "Fats","Fruits & Veggies"

    } ;
    int[] gridViewImageId = {
            R.drawable.proteins, R.drawable.carbs, R.drawable.fats, R.drawable.fruitveggies
    };

    private ListView myDrawerList;
    private ArrayAdapter<String> arrAdapter;

    private ActionBarDrawerToggle myDrawerToggle;
    private DrawerLayout myDrawerLayout;
    private String myActivityTitle;

    private void addDrawerItems() {
        String[] menuArray = {"Exercises", "Workouts", "Nutrition", "Supplements", "Progress Tracker" ,"Log Out"};
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
        myDrawerList.setAdapter(arrAdapter);

        myDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(NutritionActivity.this, ExerciseActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(NutritionActivity.this, WorkoutActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(NutritionActivity.this, NutritionActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(NutritionActivity.this, SupplementActivity.class));
                        break;
                    case 4:
                        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NutritionActivity.this);
                        final String result = prefs.getString("RegisteredUser","null");

                        if(result.compareTo("true")!=0){
                            Toast myToast =  Toast.makeText(NutritionActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                            myToast.show();
                        }
                        else{
                            startActivity(new Intent(NutritionActivity.this, ProgressTrackerActivity.class));
                        }

                        break;
                    case 5:

                        /////-----------------call to shared pref to log out
                        editSharedPrefs();
                        /////logging out from facebook
                        try {
                            FacebookSdk.sdkInitialize(getApplicationContext());
                            LoginManager.getInstance().logOut();
                        }catch (Exception ex){
                            Toast myToast =  Toast.makeText(NutritionActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                            myToast.show();
                        }
                        startActivity(new Intent(NutritionActivity.this, LoginActivity.class));
                        break;
                }
            }
        });
    }

    private void editSharedPrefs(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("IsLogged", "false");
        editor.putString("RegisteredUser","false");
        editor.putString("UserEmail","null");
        //editor.putString("BrowseRecsResult", browseRecsOutput.toString());
        editor.commit();
    }


    private void setupDrawer() {
        myDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(myActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        myDrawerToggle.setDrawerIndicatorEnabled(true);
        myDrawerLayout.setDrawerListener(myDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        myDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (myDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    String textContent;
    String textHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);


        myDrawerList = (ListView)findViewById(R.id.navList);
        myDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        myActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        final TextView contentDesc = (TextView)findViewById(R.id.textViewDesc);
        contentDesc.setMovementMethod(new ScrollingMovementMethod());

        CustomGridViewAdapter adapterViewAndroid = new CustomGridViewAdapter(NutritionActivity.this, gridViewString, gridViewImageId);
        androidGridView = (GridView)findViewById(R.id.grid_view_images_texts);

        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(NutritionActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                switch (i){
                    case 0:
                        textContent = "<b>Protein Options / Serving Size</b>" + "<br/></br/><br/>" +
                         "<b>Eggs:</b> 6 egg whites (you can boil them or cook them in a pan using non stick spray like PAM. Season with salt and pepper or a little hot sauce to taste)<br/><br/>" +
                                "<b>Low Fat Cottage Cheese:</b> 1 cup <br/><br/>" +
                                "<b>Chick Breast:</b> 1 breast (grill, bake, or pan fry with non-stick spray.  Okay to season but DO NOT use marinades because they are full of salt)<br/><br/>" +
                                "<b>Lean Beef:</b> 6 oz (Ground Beef 93% lean, Eye of Round cut in to small portions and grilled, baked, fried -or-  London Broil grilled or baked then cut into portions)<br/><br/>" +
                                "<b>Pork:</b> 6 oz (Pork Chops lean with fat cut off edge before cooking -or- Pork Tenderloin <br/><br/>" +
                                "<b>Fish:</b> 6 oz (any fish is acceptable)<br/><br/>" +
                                "<b>Protein Powder Mixed with Water:</b> 1 scoop (around 25g of protein per serving)<br/><br/>" +
                                "<b>Deli Meat:</b> Turkey 4 oz. (ask for the low sodium) <br/><br/>";
                        contentDesc.setText(Html.fromHtml(textContent));

                        break;
                    case 1:
                        textContent = "<b>Carbohydrate Options / Serving Size</b>" + "<br/></br/><br/>" +
                                "<b>Oatmeal:</b> 3/4 cup (make with water and season with artificial sweetener such as Stevia, Splenda, Equal, Sweet n Low, or cinnamon. After cooking, I add a scoop of protein powder to create a chocolate protein oatmeal which is awesome.)<br/><br/>" +
                                "<b>High Fiber Cereal:</b> 1 cup with 1/2 cup skim milk<br/><br/>" +
                                "<b>Sweet Potato:</b> medium size (microwave wrapped in a paper towel for 5 to 6 minutes on high) <br/><br/>" +
                                "<b>Brown Rice:</b> 1 cup <br/><br/>" +
                                "<b>Quinoa:</b> 1 cup (actually a seed that is very similar in texture and consistency to couscous. Season with salt & pepper which is quite good)<br/><br/>" +
                                "<b>Wheat Berries:</b> 1 cup (incredible grain found in bulk at grocers such as Whole Foods) <br/><br/>" +
                                "<b>Whole Grain Bread:</b> 2 slices <br/><br/>" +
                                "<b>Low Carbohydrate or Whole Wheat Tortilla:</b> 1 <br/><br/>" +
                                "<b>Beans:</b> 1/2 can (pinto, garbanzo, or red kidney and look for the low sodium option if buying canned beans)<br/><br/>";
                        contentDesc.setText(Html.fromHtml(textContent));
                        break;

                    case 2:
                        textContent = "<b>Fat Options</b> <b>/</b> <b>Serving Size</b>" + "<br/></br/><br/>" +
                                "<b>Olive Oil </b> <b>/</b> <b>Flaxseed Oil:</b> Great source of fat. Great to add to dishes.<br/><br/>" +
                                "<b>Fish Oil:</b> Staple source of healthy fats.<br/><br/>" +
                                "<b>Almond Butter</b><b>/</b><b>Cashew Butter:</b> Derived from the nuts but a good source of fat.<br/><br/>" +
                                "<b>Almonds:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Pecans:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Walnuts:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Cashews:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Natural Peanut Butter:</b> Not processed. Great source of fat and some protein. Love adding this to my shakes during the day.<br/><br/>" +
                                "<b>Avocados:</b> Healthy fat source. Love it in my sushi.";
                        contentDesc.setText(Html.fromHtml(textContent));
                        break;
                    case 3:
                        textContent = "<b>Vegetables</b>" + "<br/><br/><br/>" +
                                "General rule is that if it is *green* you may consume as much as desired- seriously as much as you want.<br/><br/>" +
                                "Salads are a great option; however, use a vinegar dressing as opposed to high calorie dressings.<br/><br/> " +
                                "Seasoned Rice Wine Vinegar is a personal favorite which can be found in the vinegar section of the grocery store.<br/><br/>" +
                                "<b>Broccoli:</b> as much as you want (great steamed with a little salt, pepper and a squeeze of lemon)<br/><br/>" +
                                "<b>Yellow Squash:</b> as much as you want<br/><br/>" +
                                "<b>Zucchini:</b> as much as you want<br/><br/>" +
                                "<b>Green Pepper:</b> as much as you want<br/><br/>" +
                                "<b>Celery:</b> as much as you want<br/><br/>" +
                                "<b>Cucumber:</b> as much as you want<br/><br/>" +
                                "<b>Spinach:</b> as much as you want<br/><br/>" +
                                "<b>Lettuce (any kind):</b> as much as you want<br/><br/>" +
                                "<b>Green Beans:</b> as much as you want (if canned, purchase the \"<i>No Sodium Added<i/>\" option)<br/><br/>" +
                                "<b>Asparagus:</b> as much as you want<br/><br/>" +
                                "<b>Radishes:</b> as much as you want<br/><br/>" +
                                "<b>Mushrooms:</b> as much as you want<br/><br/>" +
                                "<b>Carrots:</b></b> 2 medium size (note this root contains more sugar than most green vegetables) <br/><br/><br/>" +
                                "<b>Fruits</b><br/><br/><br/>" +
                                "<b>Orange:</b> 1<br/><br/>" +
                                "<b>Apple:</b> 1<br/><br/>" +
                                "<b>Grapefruit:</b> 1<br/><br/>" +
                                "<b>Peach:</b> 1<br/><br/>" +
                                "<b>Nectarine:</b> 1<br/><br/>" +
                                "<b>Pear:</b> 1<br/><br/>" +
                                "<b>Kiwi:</b> 2<br/><br/>" +
                                "<b>Tangerine</b>: 2";
                        contentDesc.setText(Html.fromHtml(textContent));
                        break;
                }

            }
        });

    }
}
