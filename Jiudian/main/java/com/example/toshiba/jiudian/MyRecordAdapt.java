package com.example.toshiba.jiudian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 2017/12/30.
 */

public class MyRecordAdapt extends RecyclerView.Adapter<MyRecordAdapt.MyHolder> implements View.OnClickListener{

    List<DBrecord> list;
    Context m;//没什么必要parent.getContext()就可以获取
//    ArrayList<Integer> temp;
//    int temp;
    ButtonInterface buttonInterface=null;

    public MyRecordAdapt(Context context,List<DBrecord> records){
        m=context;
        list=records;
//        temp=new ArrayList<>();
    }

    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public static interface ButtonInterface{
        void onCancel(View view,int position,List<DBrecord> dBrecordList);
        void onPay(View view,int position);
    }

    private  OnItemClickListener onItemClickListener = null;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(m).inflate(R.layout.record_item,null);
        MyHolder vh=new MyHolder(v);
        v.setOnClickListener(this);

        return vh;
    }

    @Override
    public void onClick(View v){
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(v,(int)v.getTag());
        }
        if(buttonInterface!=null){
        switch (v.getId()) {
            case R.id.pay:
                buttonInterface.onPay(v, (int) v.getTag());
                break;
            case R.id.cancel:
                buttonInterface.onCancel(v, (int) v.getTag(), list);
                notifyDataSetChanged();
                break;
        }
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.holderposition=position;
//        temp.add(position);
//        int temp=position;
//        temp=position;

        holder.hotel_name.setText(list.get(position).getHotel_name());//原来打印position啥都没有
        holder.room_spent.setText(list.get(position).getRoom_spent()+"元");
        holder.date_in.setText(list.get(position).getDate_in()+" ");
        holder.date_last.setText(list.get(position).getDate_in()+"之后的六小时内");//
        holder.pay_condition.setText(list.get(position).getPay_condition());
//        Glide.with(m).load()
        holder.pay.setOnClickListener(this);
        holder.cancel.setOnClickListener(this);

        holder.pay.setTag(position);
        holder.cancel.setTag(position);
        holder.view.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();//这里~~~~~~~~~~~指定item的个数
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener=listener;
    }
    public void setOnClickLisener(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView hotel_name;
        private TextView pay_condition;
        private TextView room_spent;
        private TextView date_in;
        private TextView date_last;
        private Button pay;
        private Button cancel;
//        private int holderposition;

        public MyHolder(View v){
            super(v);
//            textView = (TextView) v.findViewById(R.id.text1);
            view=v;
            pay_condition=(TextView) v.findViewById(R.id.pay_condition);
            hotel_name=(TextView) v.findViewById(R.id.hotel_name);
            room_spent=(TextView) v.findViewById(R.id.room_spent);
            date_in=(TextView) v.findViewById(R.id.date_in);
            date_last=(TextView) v.findViewById(R.id.date_last);
            pay=(Button) v.findViewById(R.id.pay);
            cancel=(Button) v.findViewById(R.id.cancel);
//            holderposition=v.get
        }
    }

}
