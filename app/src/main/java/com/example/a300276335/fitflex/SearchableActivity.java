package com.example.a300276335.fitflex;

import android.app.SearchManager;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationview;
    private View headerview;

    String[] listviewTitle = new String[]{
            "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4",
            "ListView Title 5"
    };


    int[] listviewImage = new int[]{
            R.drawable.android, R.drawable.android, R.drawable.android, R.drawable.android,
            R.drawable.android
    };

    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description"
    };


    private ListView androidListView;
    private List<HashMap<String, String>> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        setupToolbarMenu();
        setupNavigationDrawerMenu();
        setNavigationViewListner();

        aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.listview_layout, from, to);
        androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);


        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }


    private void doMySearch(String query) {
        SimpleAdapter simpleAdapter = (SimpleAdapter) androidListView.getAdapter();

        switch (query.toLowerCase().trim()) {
            case "whey":
            case "whey protein":
            case "protein":
            case "whey proteins":
            case "proteins":

                androidListView.setVisibility(View.INVISIBLE);
                listviewTitle = new String[]{
                        "Syntha-6\n" +
                                "BSN", "NITRO-TECH 100% Whey Gold\n" +
                        "MuscleTech", "Stacked Protein\n" +
                        "EVLUTION NUTRITION", "COR-Performance Whey\n" +
                        "Cellucor",
                        "Combat Powder\n" +
                                "MusclePharm"
                };


                listviewImage = new int[]{
                        R.drawable.protein1, R.drawable.protein2, R.drawable.protein3, R.drawable.protein4,
                        R.drawable.protein5
                };

                listviewShortDescription = new String[]{
                        "22g of an Ultra-Premium Blended Protein Formula for Use Anytime, Day or Night", "Each Scoop Serves Up 24G Of Ultra-Premium Micro-Filtered Protein", "25 Gram Protein Complex With Milkshake Taste, With 5g BCAAs and 5g Glutamine To Fuel Your Results!", "Great Tasting Protein with Minimal Fat and Carbs and Added Digestive Enzymes", "Protein Powder Blend for Muscle Growth"

                };
                aList.clear();


                for (int j = 0; j < 5; j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", listviewTitle[j]);
                    hm.put("listview_discription", listviewShortDescription[j]);
                    hm.put("listview_image", Integer.toString(listviewImage[j]));
                    aList.add(hm);
                }

                simpleAdapter.notifyDataSetChanged();
                androidListView.setVisibility(View.VISIBLE);
                break;

            case "casein":
            case "casein protein":
            case "casein proteins":

                androidListView.setVisibility(View.INVISIBLE);
                listviewTitle = new String[]{
                        "Gold Standard 100% Casein\n" +
                                "Optimum Nutrition", "NITRO-TECH Casein Gold\n" +
                        "MuscleTech", "Elite Casein\n" +
                        "Dymatize", "Kasein\n" +
                        "Kaged Muscle",
                        "Combat 100% Casein\n" +
                                "MusclePharm"
                };


                listviewImage = new int[]{
                        R.drawable.casein1, R.drawable.casein2, R.drawable.casein3, R.drawable.casein4,
                        R.drawable.casein5
                };

                listviewShortDescription = new String[]{
                        "Nighttime Use Protein Powder for Building Muscle", "Micellar Casein Protein For Supporting Muscle Growth", "25g of Protein to Continuously Feeds Muscles, Even When You Can’t", "Ultra-Premium, Cold-Processed Micellar Casein Isolate", "Slow Digesting Casein Protein Powder"
                };
                aList.clear();


                for (int j = 0; j < 5; j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", listviewTitle[j]);
                    hm.put("listview_discription", listviewShortDescription[j]);
                    hm.put("listview_image", Integer.toString(listviewImage[j]));
                    aList.add(hm);
                }

                simpleAdapter.notifyDataSetChanged();
                androidListView.setVisibility(View.VISIBLE);
                break;

            case "multivitamins":
            case "multi":
            case "vitamins":

                androidListView.setVisibility(View.INVISIBLE);
                listviewTitle = new String[]{
                        "Opti-Men\n" +
                                "Optimum Nutrition", "Platinum Multivitamin\n" +
                        "MuscleTech", "VitaMode\n" +
                        "EVLUTION NUTRITION", "Animal Pak\n" +
                        "Universal Nutrition", "Vita JYM\n" +
                        "JYM Supplement Science"
                };


                listviewImage = new int[]{
                        R.drawable.multivitamins1, R.drawable.multivitamins2, R.drawable.multivitamins3, R.drawable.multivitamins4,
                        R.drawable.multivitamins5
                };

                listviewShortDescription = new String[]{"Active Ingredients and Essential Daily Nutrients in One Convenient Table", "18 Vitamins and Minerals with 865mg of Amino Acids to Support General Health", "High Performance Multi-Vitamin for Overall Health", "Supports Rigorous Training for All Competitive Strength and Power Athletes", "Science Based Formula Provides Micronutrients Athletes Need to Support Growth and Overall Well-Being"
                };
                aList.clear();


                for (int j = 0; j < 5; j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", listviewTitle[j]);
                    hm.put("listview_discription", listviewShortDescription[j]);
                    hm.put("listview_image", Integer.toString(listviewImage[j]));
                    aList.add(hm);
                }

                simpleAdapter.notifyDataSetChanged();
                androidListView.setVisibility(View.VISIBLE);
                break;

            case "fish oil":
            case "fish oils":
            case "omega":
            case "omega3":
            case "fish":
            case "oils":

                androidListView.setVisibility(View.INVISIBLE);
                listviewTitle = new String[]{
                        "Fish Oil\n" +
                                "EVLUTION NUTRITION", "Fish Oil Softgels\n" +
                        "Optimum Nutrition", "Platinum 100% Fish Oil\n" +
                        "MuscleTech", "Omega Jym\n" +
                        "JYM Supplement Science", "Signature Fish Oil\n" +
                        "Bodybuilding.com"
                };


                listviewImage = new int[]{
                        R.drawable.omega1, R.drawable.omega2, R.drawable.omega3, R.drawable.omega4,
                        R.drawable.omega5
                };

                listviewShortDescription = new String[]{
                        "100% Highly Purified Triple Strength Fish Oil Softgels", "Packed with Omega-3 Essential Fatty Acids", "Ultra-Pure Filtered Fish Oil Softgel", "The Alpha of OMEGA 3's", "Supports Overall Wellness"
                };
                aList.clear();


                for (int j = 0; j < 5; j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", listviewTitle[j]);
                    hm.put("listview_discription", listviewShortDescription[j]);
                    hm.put("listview_image", Integer.toString(listviewImage[j]));
                    aList.add(hm);
                }

                simpleAdapter.notifyDataSetChanged();
                androidListView.setVisibility(View.VISIBLE);
                break;

            case "preworkout":
            case "preworkouts":
            case "preworkout drink":
            case "preworkout drinks":
            case "pre-workout":
            case "pre-workouts":

                //suppsTitle.setText("Top 5 Preworkout Drinks");
                androidListView.setVisibility(View.INVISIBLE);
                listviewTitle = new String[]{
                        "Cellucor\n" +
                                "C4 Original", "JYM Supplement Science\n" +
                        "Pre JYM", "EVLUTION NUTRITION\n" +
                        "ENGN", "EVLUTION NUTRITION\n" +
                        "ENGN Shred", "Kaged Muscle\n" +
                        "PRE-KAGED"
                };


                listviewImage = new int[]{
                        R.drawable.preworkout1, R.drawable.preworkout2, R.drawable.preworkout3, R.drawable.preworkout4,
                        R.drawable.preworkout5
                };

                listviewShortDescription = new String[]{
                        "An Advanced Pre-Workout Formulated for Anyone Seeking Increased Energy and Focus", "Pre-Workout Powder Powerhouse Packed with 13-Hand Picked Ingredients to Support Improved Workouts", "Intense Pre-Workout Powder for Increased Energy, Power, & Focus", "Advanced Pre-Workout", "Turn Up Your Workout Intensity and Increase Performance with a Revolutionary Pre-Workout Primer"
                };
                aList.clear();


                for (int j = 0; j < 5; j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", listviewTitle[j]);
                    hm.put("listview_discription", listviewShortDescription[j]);
                    hm.put("listview_image", Integer.toString(listviewImage[j]));
                    aList.add(hm);
                }

                simpleAdapter.notifyDataSetChanged();
                androidListView.setVisibility(View.VISIBLE);
                break;

            case "aminos":
            case "amino":
            case "intraworkout":
            case "intraworkouts":
            case "intraworkout drink":
            case "intraworkout drinks":
            case "intra-workout":
            case "intra-workouts":
                //suppsTitle.setText("Top 5 Aminos");
                androidListView.setVisibility(View.INVISIBLE);
                listviewTitle = new String[]{
                        "BCAA Energy\n" +
                                "EVLUTION NUTRITION", "Essential AmiN.O. Energy\n" +
                        "Optimum Nutrition", "AminoLean Energy Formula\n" +
                        "RSP Nutrition", "Xtend\n" +
                        "SciVation", "BCAA Lean Energy\n" +
                        "EVLUTION NUTRITION"
                };


                listviewImage = new int[]{
                        R.drawable.aminos1, R.drawable.aminos2, R.drawable.aminos3, R.drawable.aminos4,
                        R.drawable.aminos5
                };

                listviewShortDescription = new String[]{
                        "BCAA Powder with Natural Energizers Sourced from Green Coffee and Green Tea To Support Focus and Recovery", "Amino Acid Powder with 5g of Free Form Amino Acids and 100mg of Caffeine from Natural Sources for Increased Energy and Focus", "Essential Amino Acids Formulated with Caffeine from Natural Sources to Support Building Lean Muscle", "Intra-Workout Powder with 7g of BCAAs to Build Muscle, Burn Fat, and Aid Recovery During Workouts", "Bcaas + Energy + Weight Management"
                };
                aList.clear();


                for (int j = 0; j < 5; j++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", listviewTitle[j]);
                    hm.put("listview_discription", listviewShortDescription[j]);
                    hm.put("listview_image", Integer.toString(listviewImage[j]));
                    aList.add(hm);
                }

                simpleAdapter.notifyDataSetChanged();
                androidListView.setVisibility(View.VISIBLE);
                break;


        }
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
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SearchableActivity.this);
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
                startActivity(new Intent(SearchableActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        //Toast.makeText(MainActivity.this, itemName + " Clicked",Toast.LENGTH_SHORT).show();


        switch(menuItem.getItemId()){

            case R.id.item_exercises:
                startActivity(new Intent(SearchableActivity.this, ExerciseActivity.class));
                break;

            case R.id.item_workout:
                startActivity(new Intent(SearchableActivity.this, WorkoutActivity.class));
                break;

            case R.id.item_nutrition:
                startActivity(new Intent(SearchableActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_suppliment:
                startActivity(new Intent(SearchableActivity.this,NutriSuppsActivity.class));
                break;

            case R.id.item_progress:
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SearchableActivity.this);
                final String result = prefs.getString("RegisteredUser","null");

                if(result.compareTo("true")!=0){
                    Toast myToast =  Toast.makeText(SearchableActivity.this, "Only for Registered Users", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else{
                    startActivity(new Intent(SearchableActivity.this, ProgressTrackerActivity.class));
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
                    Toast myToast =  Toast.makeText(SearchableActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                    myToast.show();
                }
                startActivity(new Intent(SearchableActivity.this, LoginActivity.class));
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
