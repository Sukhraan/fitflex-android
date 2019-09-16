package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.a300276335.fitflex.NutritionFragment;
import com.example.a300276335.fitflex.SupplementFragment;

/**
 * Created by 300276335 on 8/2/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NutritionFragment tab1 = new NutritionFragment();
                return tab1;
            case 1:
                SupplementFragment tab2 = new SupplementFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}
