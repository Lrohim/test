package com.example.toshiba.exweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 2017/12/28.
 */

public class MyAdapt extends RecyclerView.Adapter<MyAdapt.MyHolder> implements View.OnClickListener{

    //int[] a;
    Context m;//没什么必要parent.getContext()就可以获取
    List<String> mylist=null;

    public MyAdapt(Context context,List<String> list){
        mylist=new ArrayList<>();
        m=context;
        mylist=list;
    }

    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private  OnItemClickListener onItemClickListener = null;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(m).inflate(R.layout.text,null);
        MyHolder vh=new MyHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onClick(View v){
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.textView.setText(" "+position);//原来打印position啥都没有
        holder.city.setText(mylist.get(position));

        holder.city.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mylist.size();//这里~~~~~~~~~~~指定item的个数
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener=listener;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private TextView city;

        public MyHolder(View v){
            super(v);
            city = (TextView) v.findViewById(R.id.city);
        }
    }

}