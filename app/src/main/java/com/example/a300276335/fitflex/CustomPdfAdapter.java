package com.example.a300276335.fitflex;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 300276577 on 4/5/2018.
 * Custom Adapter used in WorkoutActivity
 */

public class CustomPdfAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Integer> picture;
    ArrayList<String> title;
    ArrayList<String> description;

    public CustomPdfAdapter (Activity myContext, ArrayList<Integer> myPicture,
                             ArrayList<String> myTitle, ArrayList<String> myDesc){
        super();
        this.context = myContext;
        this.picture = myPicture;
        this.title = myTitle;
        this.description = myDesc;
    }
    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return title.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        if(view == null){
            view = inflater.inflate(R.layout.pdf_layout,null);
        }

        TextView txtViewPdf = (TextView) view.findViewById(R.id.txtItem_pdf);
        txtViewPdf.setText(title.get(i));
        TextView txtViewPdfDesc = (TextView) view.findViewById(R.id.txtItem_pdfDesc);
        txtViewPdfDesc.setText(description.get(i));

        Drawable image = ContextCompat.getDrawable(context,picture.get(i));
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        txtViewPdf.setCompoundDrawables(image, null, null, null);
        txtViewPdf.setCompoundDrawablePadding(100);
        txtViewPdfDesc.setCompoundDrawables(null,null,null,null);
        txtViewPdfDesc.setPadding(230,0,0,0);

        return view;
    }
}
