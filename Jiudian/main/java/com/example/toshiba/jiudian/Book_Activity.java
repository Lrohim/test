package com.example.toshiba.jiudian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiu on 2018/1/1.
 */

public class Book_Activity extends AppCompatActivity {

    private RecyclerView mRV;
    private List<hotelData> list = new ArrayList<>();
    String hotelname;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_list);

        Intent intent = getIntent();
        hotelname = intent.getStringExtra("name");
        init();

        mRV = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRV.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(this,list);
        mRV.setAdapter(myAdapter);
    }

    private void init() {
        for(int i=0;i<8;i++) {
            hotelData data = new hotelData(R.drawable.room_1,hotelname,"豪华单人间",
                    "30m² 大床 4点评","满10送1","¥188","可返");
            list.add(data);
        }
    }



}
