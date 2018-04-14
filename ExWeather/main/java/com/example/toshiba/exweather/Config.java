package com.example.toshiba.exweather;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Toshiba on 2017/12/28.
 */

public class Config {

    List<Weather> weathers=new ArrayList<>();
    String location;
    public Config(String location){
        this.location=location;
    }

    public boolean Canget(){
        if(weathers.size()==0){return false;}
        return true;
    }

    public List<Weather> getWeather(){
        return this.weathers;
    }
    public void send(){
        connect(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("info","扎心了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responses=response.body().string();
                parse(responses);
            }
        });
    }
    public void parse(String response){
        String temp = response.split("\"daily_forecast\":")[1];
        temp = temp.replace("}]}]}", "}]");
        while (weathers.size()==0) {
            try {
                Gson gson=new Gson();
                weathers = gson.fromJson(temp, new TypeToken<List<Weather>>() {
                }.getType());
            }catch (NullPointerException e){
                continue;
            }
        }
    }

    public void connect(Callback callback){
        String param="key=ad95b70ee31543e3b0420c864ced1a05&location="+location;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://free-api.heweather.com/s6/weather/forecast?"+param).build();
        client.newCall(request).enqueue(callback);
    }
}
