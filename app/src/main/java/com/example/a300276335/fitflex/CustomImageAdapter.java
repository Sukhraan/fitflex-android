package com.example.a300276335.fitflex;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 300276577 on 3/16/2018.
 * Another Custom Adapter
 */

public class CustomImageAdapter extends BaseAdapter {
    private Activity context;
    private String [] videoTitle;
    private String [] videoDesc;
    private int [] videoImage;

    public CustomImageAdapter(Activity anyContext, String [] anyVideoTitle, String [] anyVideoDesc,
                              int [] anyVideoImage){
        super();
        this.context = anyContext;
        this.videoTitle = anyVideoTitle;
        this.videoDesc = anyVideoDesc;
        this.videoImage = anyVideoImage;
    }

    @Override
    public int getCount() {
        return videoTitle.length;
    }

    @Override
    public Object getItem(int i) {
        return videoTitle[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        if(view == null){
            view = inflater.inflate(R.layout.view_item_layout, null);
        }
        TextView myTextView = (TextView)view.findViewById(R.id.txtViewMyItem);
        TextView myDesc = (TextView)view.findViewById(R.id.txtViewDesc);
        myTextView.setText(videoTitle[i]);
        myDesc.setText(videoDesc[i]);

        Drawable img = ContextCompat.getDrawable(context, videoImage[i]);
        img.setBounds(0,0,500,250);
        //img.setBounds(0,0,img.getIntrinsicWidth(), img.getIntrinsicHeight());
        myTextView.setCompoundDrawables(img,null,null,null);
        myTextView.setCompoundDrawablePadding(100);
        //myTextView.setCompoundDrawablePadding(50);
        myDesc.setCompoundDrawables(null,null,null,null);
        myDesc.setPadding(600,10,10,0);
        //myDesc.setPadding(500,10,10,0);
        return view;
    }
}
