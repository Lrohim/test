package com.example.toshiba.zhuce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.zhuce.R;

import java.util.List;

/**
 * Created by Toshiba on 2017/9/9.
 */

public class GoodsShowList extends ArrayAdapter<Goodslist> {

    int resourceID;
    Goodslist g;
    Context mcontext;
//    boolean mcheck;
//    List<Goodslist> one;

    public GoodsShowList(Context context, int text, List<Goodslist> list){
        super(context,text,list);
        this.resourceID=text;
        this.mcontext=context;
        //one=list;
    }

    public View getView(final int position, View convertView, final ViewGroup parent){

        View view;
        g=getItem(position);
        ViewHolder2 viewHolder;


        if(convertView==null) {
            //防止重复
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);

            viewHolder = new ViewHolder2();

            viewHolder.goodsID = (TextView) view.findViewById(R.id.part_goodsid);
            viewHolder.goodsName= (TextView) view.findViewById(R.id.part_goodsname);
            viewHolder.goodsNum = (TextView) view.findViewById(R.id.part_goodsnum);
            viewHolder.goodsPic = (ImageView) view.findViewById(R.id.img);
            viewHolder.goodsStock= (TextView) view.findViewById(R.id.part_goodsStock);
            viewHolder.goodsPrice = (TextView) view.findViewById(R.id.part_goodssum);
            viewHolder.num_add= (Button) view.findViewById(R.id.num_add);
            viewHolder.num_des=(Button) view.findViewById(R.id.num_des);
//            viewHolder.deleteInf= (Button) view.findViewById(R.id.part_delete);

            view.setTag(viewHolder);
        }else {
            //获取当前recond实例
            view=convertView;
            viewHolder=(ViewHolder2) view.getTag();
        }
                ((TextView) view.findViewById(R.id.part_goodsid)).setText(g.getGoodsId());
                ((TextView) view.findViewById(R.id.part_goodsname)).setText(g.getGoodsName());
                ((TextView) view.findViewById(R.id.part_goodsnum)).setText("" + g.getgoodsNum());
                ((TextView) view.findViewById(R.id.part_goodssum)).setText("" + g.getGoodsprice());
                ((TextView) view.findViewById(R.id.part_goodsStock)).setText(""+g.getGoodsStock());
                ((ImageView) view.findViewById(R.id.img)).setImageResource(g.getGoodsImage());
                ((Button) view.findViewById(R.id.part_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsOP gp = new GoodsOP();
                gp.DeleteById(g.getGoodsId());
                GoodsShowList.super.remove(getItem(position));
                //Goodslist_show a=new Goodslist_show();
                notifyDataSetChanged();
//                mcheck=false;
            }
        });
        ((Button) view.findViewById(R.id.num_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsOP gp=new GoodsOP();
                if(GoodsShowList.super.getItem(position).getgoodsNum()<GoodsShowList.super.getItem(position).getGoodsStock()) {//上限
                    gp.UpdateById(g.getGoodsId(), g.getgoodsNum() + 1);
                    GoodsShowList.super.getItem(position).setGoodsNum(GoodsShowList.super.getItem(position).getgoodsNum() + 1);
                    notifyDataSetChanged();
                }
            }
        });
        ((Button) view.findViewById(R.id.num_des)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsOP gp=new GoodsOP();
                if(GoodsShowList.super.getItem(position).getgoodsNum()>0) {
                    gp.UpdateById(g.getGoodsId(), g.getgoodsNum() - 1);
                    GoodsShowList.super.getItem(position).setGoodsNum(GoodsShowList.super.getItem(position).getgoodsNum() - 1);
                    notifyDataSetChanged();
                }
            }
        });
            return view;
    }
    class ViewHolder2{
        TextView goodsID;
        TextView goodsNum;
        ImageView goodsPic;
        TextView goodsPrice;
        TextView goodsName;
        TextView goodsStock;
//        Button deleteInf;
        Button num_add;
        Button num_des;
    }
}
