package com.example.toshiba.tuixiangzi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.widget.LinearLayout;

import static android.view.GestureDetector.*;

/**
 * Created by Toshiba on 2017/11/23.
 */

public class play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youxijiemian);

        String a1=getIntent().getExtras().getString("mapArray");
        ((youxizhujiemian)findViewById(R.id.view1)).guanka(a1);
       // ((LinearLayout) findViewById(R.id.father)).addView(te);
    }
}
