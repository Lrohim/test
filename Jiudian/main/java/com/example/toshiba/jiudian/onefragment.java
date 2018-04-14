package com.example.toshiba.jiudian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 2018/1/1.
 */

public class onefragment extends Fragment {
    List<DBrecord> records;
    RecyclerView recyclerView1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.onefragment,container,false);
        records= new ArrayList<>();
        records= DataSupport.findAll(DBrecord.class);

        recyclerView1=(RecyclerView) view.findViewById(R.id.one_list);
        MyRecordAdapt myRecordAdapt=new MyRecordAdapt(view.getContext(),records);
        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView1.setAdapter(myRecordAdapt);
        myRecordAdapt.setOnClickLisener(new MyRecordAdapt.ButtonInterface() {
            @Override
            public void onCancel(View view, int position, List<DBrecord> dBrecordList) {
                dBrecordList.remove(position);
            }

            @Override
            public void onPay(View view, int position) {
                Intent it=new Intent(view.getContext(),Bill.class);
                String a=records.get(position).getRoom_spent()*records.get(position).getRoom_account()+"";
                String b=records.get(position).getRoom_account()+"";
                it.putExtra("pay_condition",records.get(position).getPay_condition());
                it.putExtra("room_spent",a);
                it.putExtra("hotel_name",records.get(position).getHotel_name());
                it.putExtra("date_in",records.get(position).getDate_in());
                it.putExtra("date_out",records.get(position).getDate_out());
                it.putExtra("room_kind",records.get(position).getRoom_kind());
                it.putExtra("room_account",b);
                startActivity(it);
            }
        });
        return view;
    }
}
