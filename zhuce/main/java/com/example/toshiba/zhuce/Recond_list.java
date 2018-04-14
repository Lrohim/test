package com.example.toshiba.zhuce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Toshiba on 2017/9/6.
 */

public class Recond_list extends ArrayAdapter<RecondAttri> {

    private int resourceId;


    public  Recond_list(Context context,int text,List<RecondAttri> object){
        super(context,text,object);
        resourceId=text;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        RecondAttri f = getItem(position);
        ViewHolder viewHolder;
        if(convertView==null) {
            //防止重复
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.sumprice = (TextView) view.findViewById(R.id.recond_sum);
            viewHolder.RecordDate = (TextView) view.findViewById(R.id.recond_time);
            view.setTag(viewHolder);
        }else {
            //获取当前recond实例
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
            ((TextView) view.findViewById(R.id.recond_sum)).setText("" + f.getSumPrice());
            ((TextView) view.findViewById(R.id.recond_time)).setText(f.getRecordDate());
            return view;
        }

    class ViewHolder{
        TextView sumprice;
        TextView RecordDate;
    }
}
