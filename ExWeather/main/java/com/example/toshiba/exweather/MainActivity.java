package com.example.toshiba.exweather;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    List<String> city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout) findViewById(R.id.draw_layout);
        ActionBar actionBar=getSupportActionBar();

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);//设置图标
        }

        city=new ArrayList<String>();
        String temp1="北京";
        String temp2="上海";
        String temp3="广州";
        String temp4="深圳";

        city.add(temp1);
        city.add(temp2);
        city.add(temp3);
        city.add(temp4);


        MyAdapt myAdapt=new MyAdapt(MainActivity.this,city);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.city_list);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        recyclerView.setAdapter(myAdapt);
        myAdapt.setOnItemClickListener(new MyAdapt.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it=new Intent(MainActivity.this,Showlist.class);
                it.putExtra("city",city.get(position));
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
            drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}
