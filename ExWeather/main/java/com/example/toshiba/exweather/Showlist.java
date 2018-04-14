package com.example.toshiba.exweather;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Toshiba on 2017/12/28.
 */

public class Showlist extends AppCompatActivity {

    List<Weather> weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_weather);

        String city=getIntent().getStringExtra("city");
        Config config=new Config(city);
        config.send();
        while (!config.Canget()) {
            weathers = config.getWeather();
        }
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.weather_list);
        recyclerView.setLayoutManager(new GridLayoutManager(Showlist.this,1));
        recyclerView.setAdapter(new MyWeatherAdapt(weathers));
    }
}
