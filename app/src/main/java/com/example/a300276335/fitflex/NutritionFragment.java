package com.example.a300276335.fitflex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Adapter.CustomGridViewAdapter;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionFragment extends Fragment {


    public NutritionFragment() {
        // Required empty public constructor
    }



    GridView androidGridView;

    TextView nutritionTitle;

    String[] gridViewString = {
            "Proteins", "Carbohydrates", "Fats","Fruits & Veggies"

    } ;
    int[] gridViewImageId = {
            R.drawable.proteins, R.drawable.carbs, R.drawable.fats, R.drawable.fruitveggies
    };


    private ArrayAdapter<String> arrAdapter;




/*
    private void editSharedPrefs(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("IsLogged", "false");
        editor.putString("RegisteredUser","false");
        editor.putString("UserEmail","null");
        //editor.putString("BrowseRecsResult", browseRecsOutput.toString());
        editor.commit();
    }

*/



    String textContent;
    String textHeader;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);

                    database = FirebaseDatabase.getInstance();


        nutritionTitle = (TextView)view.findViewById(R.id.textViewNutritionTitle);


        final TextView contentDesc = (TextView)view.findViewById(R.id.textViewDesc);
        contentDesc.setMovementMethod(new ScrollingMovementMethod());

        CustomGridViewAdapter adapterViewAndroid = new CustomGridViewAdapter(getContext(), gridViewString, gridViewImageId);
        androidGridView = (GridView)view.findViewById(R.id.grid_view_images_texts);

        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(NutritionActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                switch (i){
                    case 0:
                        myRef = database.getReference("Proteins");
                        nutritionTitle.setText("Protein Sources");

                       /*
                        myRef.setValue("<b>Protein Options / Serving Size</b>" + "<br/></br/><br/>" +
                                "<b>Eggs:</b> 6 egg whites (you can boil them or cook them in a pan using non stick spray like PAM. Season with salt and pepper or a little hot sauce to taste)<br/><br/>" +
                                "<b>Low Fat Cottage Cheese:</b> 1 cup <br/><br/>" +
                                "<b>Chick Breast:</b> 1 breast (grill, bake, or pan fry with non-stick spray.  Okay to season but DO NOT use marinades because they are full of salt)<br/><br/>" +
                                "<b>Lean Beef:</b> 6 oz (Ground Beef 93% lean, Eye of Round cut in to small portions and grilled, baked, fried -or-  London Broil grilled or baked then cut into portions)<br/><br/>" +
                                "<b>Pork:</b> 6 oz (Pork Chops lean with fat cut off edge before cooking -or- Pork Tenderloin <br/><br/>" +
                                "<b>Fish:</b> 6 oz (any fish is acceptable)<br/><br/>" +
                                "<b>Protein Powder Mixed with Water:</b> 1 scoop (around 25g of protein per serving)<br/><br/>" +
                                "<b>Deli Meat:</b> Turkey 4 oz. (ask for the low sodium) <br/><br/>");



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
                        */

                        break;
                    case 1:
                        myRef = database.getReference("Carbohydrates");
                        nutritionTitle.setText("Carbohydrate Sources");

                        /*
                        myRef.setValue("<b>Carbohydrate Options / Serving Size</b>" + "<br/></br/><br/>" +
                                "<b>Oatmeal:</b> 3/4 cup (make with water and season with artificial sweetener such as Stevia, Splenda, Equal, Sweet n Low, or cinnamon. After cooking, I add a scoop of protein powder to create a chocolate protein oatmeal which is awesome.)<br/><br/>" +
                                "<b>High Fiber Cereal:</b> 1 cup with 1/2 cup skim milk<br/><br/>" +
                                "<b>Sweet Potato:</b> medium size (microwave wrapped in a paper towel for 5 to 6 minutes on high) <br/><br/>" +
                                "<b>Brown Rice:</b> 1 cup <br/><br/>" +
                                "<b>Quinoa:</b> 1 cup (actually a seed that is very similar in texture and consistency to couscous. Season with salt & pepper which is quite good)<br/><br/>" +
                                "<b>Wheat Berries:</b> 1 cup (incredible grain found in bulk at grocers such as Whole Foods) <br/><br/>" +
                                "<b>Whole Grain Bread:</b> 2 slices <br/><br/>" +
                                "<b>Low Carbohydrate or Whole Wheat Tortilla:</b> 1 <br/><br/>" +
                                "<b>Beans:</b> 1/2 can (pinto, garbanzo, or red kidney and look for the low sodium option if buying canned beans)<br/><br/>");



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
                          */
                        break;

                    case 2:
                        myRef = database.getReference("Fats");
                        nutritionTitle.setText("Fat Sources");
                        /*
                        myRef.setValue("<b>Fat Options</b> <b>/</b> <b>Serving Size</b>" + "<br/></br/><br/>" +
                                "<b>Olive Oil </b> <b>/</b> <b>Flaxseed Oil:</b> Great source of fat. Great to add to dishes.<br/><br/>" +
                                "<b>Fish Oil:</b> Staple source of healthy fats.<br/><br/>" +
                                "<b>Almond Butter</b><b>/</b><b>Cashew Butter:</b> Derived from the nuts but a good source of fat.<br/><br/>" +
                                "<b>Almonds:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Pecans:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Walnuts:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Cashews:</b> Great source of fat and small amount of protein.<br/><br/>" +
                                "<b>Natural Peanut Butter:</b> Not processed. Great source of fat and some protein. Love adding this to my shakes during the day.<br/><br/>" +
                                "<b>Avocados:</b> Healthy fat source. Love it in my sushi.");


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
                            */

                        break;
                    case 3:
                        myRef = database.getReference("FruitsAndVeggies");
                        nutritionTitle.setText("Fruits & Vegetables");
                       /*
                        myRef.setValue("<b>Vegetables</b>" + "<br/><br/><br/>" +
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
                                "<b>Tangerine</b>: 2");



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
                                  */
                        break;
                }

                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        contentDesc.setText(Html.fromHtml(value));

                        Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
        });


        return view;
    }

}
