package com.example.toshiba.exweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Toshiba on 2018/1/2.
 */

public class MyWeatherAdapt extends RecyclerView.Adapter<MyWeatherAdapt.MyHolder> {

    List<Weather> weathers;

    public MyWeatherAdapt(List<Weather> weathers) {
        this.weathers=weathers;
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView tmp_min;
        private TextView tmp_max;
        private TextView wind_spd;
        private TextView wind_dir;
        private TextView wind_sc;
        private TextView cond;

        public MyHolder(View v){
            super(v);
            date = (TextView) v.findViewById(R.id.date);
            tmp_min = (TextView) v.findViewById(R.id.tmp_min);
            tmp_max = (TextView) v.findViewById(R.id.tmp_max);
            wind_dir = (TextView) v.findViewById(R.id.wind_dir);
            wind_spd = (TextView) v.findViewById(R.id.wind_spd);
            wind_sc = (TextView) v.findViewById(R.id.wind_sc);
            cond = (TextView) v.findViewById(R.id.cond);
        }
    }
    @Override
    public MyWeatherAdapt.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,null);
        MyWeatherAdapt.MyHolder vh=new MyWeatherAdapt.MyHolder(v);
//        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.date.setText(weathers.get(position).getDate()+"");
        holder.tmp_min.setText(weathers.get(position).getTmp_min()+"°C");
        holder.tmp_max.setText(weathers.get(position).getTmp_max()+"°C");
        holder.wind_dir.setText(weathers.get(position).getWind_dir()+"");
        holder.wind_spd.setText(weathers.get(position).getWind_spd()+"级");
        holder.wind_sc.setText(weathers.get(position).getWind_sc()+"");
        holder.cond.setText(weathers.get(position).getCond_txt_d()+"");
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
