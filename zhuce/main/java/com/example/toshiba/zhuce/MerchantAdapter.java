package com.example.toshiba.zhuce;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by qiu on 2017/9/12.
 */

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {

    private List<MerchantData> mList;
    public Context context;
    public static GoodsOP gp=new GoodsOP();

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton image;
        TextView name;
        TextView sales;
        TextView base;
        TextView time;
        Button add;

        public ViewHolder(View itemView) {

            super(itemView);
            image = (ImageButton)itemView.findViewById(R.id.merchant_image);//图片
            name = (TextView)itemView.findViewById(R.id.merchant_name);//商家名字
            sales = (TextView)itemView.findViewById(R.id.merchant_sale);//月授
            base = (TextView)itemView.findViewById(R.id.merchant_base);//起送
            time = (TextView)itemView.findViewById(R.id.merchant_time);//送餐时间
            add = (Button)itemView.findViewById(R.id.merchant_add);

        }
    }

    public MerchantAdapter(Context context,List<MerchantData> MerchantList){
        this.mList = MerchantList;
        this.context=context;
    }

    @Override
    public MerchantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_merchant,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MerchantAdapter.ViewHolder holder, final int position) {

        MerchantData m = mList.get(position);
        holder.image.setImageResource(m.getMerchant_image());
        holder.name.setText(""+m.getMerchant_name());
        holder.sales.setText(""+m.getMerchant_sale());
        holder.base.setText(""+m.getMerchant_base());
        holder.time.setText(""+m.getMerchant_time());



        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gp.InsertToList(mList.get(position).getGoods_id(),
                        1,
                        mList.get(position).getGoods_image(),
                        mList.get(position).getGoods_price(),
                        mList.get(position).getGoods_name(),
                        mList.get(position).getGoods_stock());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
