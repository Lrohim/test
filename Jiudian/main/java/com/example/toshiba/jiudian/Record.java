package com.example.toshiba.jiudian;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 2017/12/30.
 */

public class Record extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list);

        Toolbar toolbar=(Toolbar)findViewById(R.id.record_toolbar);
        toolbar.setTitle("酒店订单");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ((Button) findViewById(R.id.hou)).setBackgroundColor(Color.WHITE);
        ((Button) findViewById(R.id.qian)).setBackgroundColor(Color.WHITE);

        replace(new onefragment());

        ((Button) findViewById(R.id.qian)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new onefragment());
                ((Button) findViewById(R.id.qian)).setBackgroundColor(Color.GREEN);
                ((Button) findViewById(R.id.hou)).setBackgroundColor(Color.WHITE);
            }
        });

        ((Button) findViewById(R.id.hou)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new twofragment());
                ((Button) findViewById(R.id.hou)).setBackgroundColor(Color.GREEN);
                ((Button) findViewById(R.id.qian)).setBackgroundColor(Color.WHITE);
            }
        });
    }

    public void replace(Fragment fragment){
        FragmentManager f=getSupportFragmentManager();
        FragmentTransaction transaction=f.beginTransaction();
        transaction.replace(R.id.listchange,fragment);
        transaction.commit();
    }
}
