package com.example.a300276335.fitflex;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 300276577 on 3/8/2018.
 * CustomAdapter.java --- Custom Adapters used in inflating some of the views in various
 * activities
 */

public class CustomAdapter extends BaseAdapter {
    Activity context;
    ArrayList<String> menuText = new ArrayList<>();
    ArrayList<Integer> menuImages = new ArrayList<>();
    String txt;

    public CustomAdapter (Activity myContext, ArrayList<String> myMenuText,
                          ArrayList<Integer> myMenuImages){
        super();
        this.context = myContext;
        this.menuImages = myMenuImages;
        this.menuText = myMenuText;
    }
    @Override
    public int getCount() {
        return menuText.size();
    }

    @Override
    public Object getItem(int i) {
        return menuText.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.layout_item, null);
        }
        TextView myItem = (TextView)view.findViewById(R.id.txtViewItem);
        myItem.setText(menuText.get(i));
        myItem.setBackgroundResource(menuImages.get(i));
        myItem.getBackground().setAlpha(200);
        return view;
    }
}
