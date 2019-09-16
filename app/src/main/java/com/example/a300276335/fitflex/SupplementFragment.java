package com.example.a300276335.fitflex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.CustomGridViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SupplementFragment extends Fragment {


    public SupplementFragment() {
        // Required empty public constructor
    }


    GridView androidGridView;

    TextView suppsTitle;

    String[] gridViewString = {
            "Whey Proteins","Casein Proteins","Multivitamins","Omega3","Preworkouts","Aminos"

    } ;
    int[] gridViewImageId = {
            R.drawable.wheyproteins, R.drawable.caseinproteins, R.drawable.multivitamins, R.drawable.omega,R.drawable.pre4workous,R.drawable.aminos
    };


    // Array of strings for ListView
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplement, container, false);






        suppsTitle = (TextView)view.findViewById(R.id.textViewSuppTitle);

        CustomGridViewAdapter adapterViewAndroid = new CustomGridViewAdapter(getContext(), gridViewString, gridViewImageId);
        androidGridView = (GridView)view.findViewById(R.id.grid_view_supps);
        androidGridView.setAdapter(adapterViewAndroid);

        final List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        final SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), aList, R.layout.listview_layout, from, to);
        final ListView androidListView = (ListView) view.findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);


        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(SupplementActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                SimpleAdapter simpleAdapter = (SimpleAdapter)androidListView.getAdapter();

                switch(i){
                    case 0:
                        suppsTitle.setText("Top 5 Whey Proteins");
                        androidListView.setVisibility(view.INVISIBLE);
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
                                "22g of an Ultra-Premium Blended Protein Formula for Use Anytime, Day or Night","Each Scoop Serves Up 24G Of Ultra-Premium Micro-Filtered Protein", "25 Gram Protein Complex With Milkshake Taste, With 5g BCAAs and 5g Glutamine To Fuel Your Results!", "Great Tasting Protein with Minimal Fat and Carbs and Added Digestive Enzymes", "Protein Powder Blend for Muscle Growth"

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
                        androidListView.setVisibility(view.VISIBLE);
                        break;

                    case 1:
                        suppsTitle.setText("Top 5 Casein Proteins");
                        androidListView.setVisibility(view.INVISIBLE);
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
                                "Nighttime Use Protein Powder for Building Muscle","Micellar Casein Protein For Supporting Muscle Growth","25g of Protein to Continuously Feeds Muscles, Even When You Can’t","Ultra-Premium, Cold-Processed Micellar Casein Isolate","Slow Digesting Casein Protein Powder"
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
                        androidListView.setVisibility(view.VISIBLE);
                        break;

                    case 2:
                        suppsTitle.setText("Top 5 Multivitamins");
                        androidListView.setVisibility(view.INVISIBLE);
                        listviewTitle = new String[]{
                                "Opti-Men\n" +
                                        "Optimum Nutrition","Platinum Multivitamin\n" +
                                "MuscleTech","VitaMode\n" +
                                "EVLUTION NUTRITION","Animal Pak\n" +
                                "Universal Nutrition","Vita JYM\n" +
                                "JYM Supplement Science"
                        };


                        listviewImage = new int[]{
                                R.drawable.multivitamins1, R.drawable.multivitamins2, R.drawable.multivitamins3, R.drawable.multivitamins4,
                                R.drawable.multivitamins5
                        };

                        listviewShortDescription = new String[]{"Active Ingredients and Essential Daily Nutrients in One Convenient Table","18 Vitamins and Minerals with 865mg of Amino Acids to Support General Health","High Performance Multi-Vitamin for Overall Health","Supports Rigorous Training for All Competitive Strength and Power Athletes","Science Based Formula Provides Micronutrients Athletes Need to Support Growth and Overall Well-Being"
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
                        androidListView.setVisibility(view.VISIBLE);
                        break;

                    case 3:
                        suppsTitle.setText("Top 5 Fish Oils");
                        androidListView.setVisibility(view.INVISIBLE);
                        listviewTitle = new String[]{
                                "Fish Oil\n" +
                                        "EVLUTION NUTRITION","Fish Oil Softgels\n" +
                                "Optimum Nutrition","Platinum 100% Fish Oil\n" +
                                "MuscleTech","Omega Jym\n" +
                                "JYM Supplement Science","Signature Fish Oil\n" +
                                "Bodybuilding.com"
                        };


                        listviewImage = new int[]{
                                R.drawable.omega1, R.drawable.omega2, R.drawable.omega3, R.drawable.omega4,
                                R.drawable.omega5
                        };

                        listviewShortDescription = new String[]{
                                "100% Highly Purified Triple Strength Fish Oil Softgels","Packed with Omega-3 Essential Fatty Acids","Ultra-Pure Filtered Fish Oil Softgel","The Alpha of OMEGA 3's","Supports Overall Wellness"
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
                        androidListView.setVisibility(view.VISIBLE);
                        break;

                    case 4:
                        suppsTitle.setText("Top 5 Preworkout Drinks");
                        androidListView.setVisibility(view.INVISIBLE);
                        listviewTitle = new String[]{
                                "Cellucor\n" +
                                        "C4 Original","JYM Supplement Science\n" +
                                "Pre JYM","EVLUTION NUTRITION\n" +
                                "ENGN","EVLUTION NUTRITION\n" +
                                "ENGN Shred","Kaged Muscle\n" +
                                "PRE-KAGED"
                        };


                        listviewImage = new int[]{
                                R.drawable.preworkout1, R.drawable.preworkout2, R.drawable.preworkout3, R.drawable.preworkout4,
                                R.drawable.preworkout5
                        };

                        listviewShortDescription = new String[]{
                                "An Advanced Pre-Workout Formulated for Anyone Seeking Increased Energy and Focus","Pre-Workout Powder Powerhouse Packed with 13-Hand Picked Ingredients to Support Improved Workouts","Intense Pre-Workout Powder for Increased Energy, Power, & Focus","Advanced Pre-Workout","Turn Up Your Workout Intensity and Increase Performance with a Revolutionary Pre-Workout Primer"
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
                        androidListView.setVisibility(view.VISIBLE);
                        break;

                    case 5:
                        suppsTitle.setText("Top 5 Aminos");
                        androidListView.setVisibility(view.INVISIBLE);
                        listviewTitle = new String[]{
                                "BCAA Energy\n" +
                                        "EVLUTION NUTRITION","Essential AmiN.O. Energy\n" +
                                "Optimum Nutrition","AminoLean Energy Formula\n" +
                                "RSP Nutrition","Xtend\n" +
                                "SciVation","BCAA Lean Energy\n" +
                                "EVLUTION NUTRITION"
                        };


                        listviewImage = new int[]{
                                R.drawable.aminos1, R.drawable.aminos2, R.drawable.aminos3, R.drawable.aminos4,
                                R.drawable.aminos5
                        };

                        listviewShortDescription = new String[]{
                                "BCAA Powder with Natural Energizers Sourced from Green Coffee and Green Tea To Support Focus and Recovery","Amino Acid Powder with 5g of Free Form Amino Acids and 100mg of Caffeine from Natural Sources for Increased Energy and Focus","Essential Amino Acids Formulated with Caffeine from Natural Sources to Support Building Lean Muscle","Intra-Workout Powder with 7g of BCAAs to Build Muscle, Burn Fat, and Aid Recovery During Workouts","Bcaas + Energy + Weight Management"
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
                        androidListView.setVisibility(view.VISIBLE);
                        break;


                }

            }
        });



        return view;
    }

}
