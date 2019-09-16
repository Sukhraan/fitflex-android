package com.example.a300276335.fitflex;
/**
 * ProgressReportActivity.java used for debugging purposes.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProgressResultActivity extends AppCompatActivity {

    TextView txtViewBrowseRecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_result);
        final TextView txtViewModifyRecs = (TextView)findViewById(R.id.txtViewModifyRecs);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String result = prefs.getString("ModifyRecsResult", "No results to show");
        txtViewModifyRecs.setText(result);

        txtViewBrowseRecs = (TextView)findViewById(R.id.txtViewBrowseRecs);
        final Button btnDispRecs = (Button)findViewById(R.id.btnShowRecs);
        btnDispRecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultShowRecs = prefs.getString("BrowseRecsResult",
                        "No records to show");
                txtViewBrowseRecs.setText(resultShowRecs);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("ShowRecsText", txtViewBrowseRecs.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected  void onRestoreInstanceState(Bundle savedInstance){
        txtViewBrowseRecs.setText(savedInstance.getString("ShowRecsText"));
    }


}
