package com.example.toshiba.jiudian;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by qiu on 2017/12/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private static final int type_top = 0,type_time = 1,type_recommend=2,type_info=3;
    private List<hotelData> list;
    private Context mcontext;

    public MyAdapter(Context context, List<hotelData> data){
        list = data;
        mcontext = context;
        Log.d("大小",""+list.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton back;
        ImageView img;
        TextView Hotel_name,name,info,price,sale,promotion;
        TextView intime,outtime,showin,showout;
        TextView img2,name2,info2,price2,about;
        TextView favour,share;
        View RoomView;

        public ViewHolder(View itemView,int viewType) {
            super(itemView);
            if(viewType == type_top){
                Hotel_name = (TextView) itemView.findViewById(R.id.hotel_name);
                back =(ImageButton) itemView.findViewById(R.id.back);
                favour =(TextView) itemView.findViewById(R.id.favour);
                share =(TextView) itemView.findViewById(R.id.bt_share);
            }else if(viewType == type_time){
                showin = (TextView)itemView.findViewById(R.id.showin);
                showout = (TextView)itemView.findViewById(R.id.showout);
                intime =(TextView) itemView.findViewById(R.id.intime);
                outtime =(TextView) itemView.findViewById(R.id.outtime);
            }else if(viewType == type_recommend) {
                img2 =(TextView) itemView.findViewById(R.id.recommend);
                name2 =(TextView) itemView.findViewById(R.id.room_name);
                info2 =(TextView) itemView.findViewById(R.id.recommend_info);
                about = (TextView)itemView.findViewById(R.id.recommend_about);
                price2 =(TextView) itemView.findViewById(R.id.recommend_price);
            }else{
                RoomView = itemView;
                img = (ImageView) itemView.findViewById(R.id.img);
                name = (TextView) itemView.findViewById(R.id.room_name);
                info = (TextView) itemView.findViewById(R.id.room_type);
                price = (TextView) itemView.findViewById(R.id.price);
                sale = (TextView) itemView.findViewById(R.id.sale);
                promotion = (TextView) itemView.findViewById(R.id.promotion);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == type_top){
            View view = LayoutInflater.from(mcontext).inflate(R.layout.layout_top,parent,false);
            Drawable drawable1 = view.getResources().getDrawable(R.drawable.favour);
            drawable1.setBounds(0,0,50,50);
            TextView favour =(TextView) view.findViewById(R.id.favour);
            favour.setCompoundDrawables(null,drawable1,null,null);
            Drawable drawable2 = view.getResources().getDrawable(R.drawable.share);
            drawable2.setBounds(0,0,50,50);
            TextView share = (TextView)view.findViewById(R.id.bt_share);
            share.setCompoundDrawables(null,drawable2,null,null);
            ViewHolder holder = new ViewHolder(view,viewType);
            return holder;
        }else if(viewType == type_time){
            View view = LayoutInflater.from(mcontext).inflate(R.layout.layout_picktime,parent,false);
            ViewHolder holder = new ViewHolder(view,viewType);
            return holder;
        }else if(viewType == type_recommend){
            View view = LayoutInflater.from(mcontext).inflate(R.layout.layout_recommend,parent,false);
            Drawable drawable = view.getResources().getDrawable(R.drawable.room_2);
            drawable.setBounds(0,0,200,180);
            TextView recommend =(TextView) view.findViewById(R.id.recommend);
            recommend.setCompoundDrawables(null,drawable,null,null);
            ViewHolder holder = new ViewHolder(view,viewType);
            return holder;
        }else{
            View view = LayoutInflater.from(mcontext).inflate(R.layout.layout_item
                    ,parent,false);
            final ViewHolder holder = new ViewHolder(view,viewType);
            holder.RoomView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext,Room_activity.class);
                    mcontext.startActivity(intent);
                }
            });
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(getItemViewType(position)==type_top){
            holder.Hotel_name.setText(list.get(position).getHotel_name());
            holder.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else if(getItemViewType(position) == type_time){
            Calendar calendar = Calendar.getInstance();

            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            holder.showin.setText((month+1)+"月"+day+"号");
            holder.showout.setText((month+1)+"月"+(day+1)+"号");

            holder.intime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(mcontext, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            holder.showin.setText((month+1)+"月"+day+"号");
                        }
                    },year,month,day);
                    datePickerDialog.show();

                    DatePicker datePicker = findDatePicker((ViewGroup)datePickerDialog.getWindow().getDecorView());
                    if(datePicker != null){
                        ((ViewGroup)((ViewGroup)datePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                    }
                }


            });

            holder.outtime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();

                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(mcontext, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            holder.showout.setText((month+1)+"月"+day+"号");
                        }
                    },year,month,day);
                    datePickerDialog.show();

                    DatePicker datePicker = findDatePicker((ViewGroup)datePickerDialog.getWindow().getDecorView());
                    if(datePicker != null){
                        ((ViewGroup)((ViewGroup)datePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                    }
                }
            });

        }else if(getItemViewType(position) == type_recommend){
            hotelData hotelData = list.get(0);
            holder.name2.setText(hotelData.getName()+"(内宾)");
            holder.info2.setText("单床    大床  4点评");
            holder.about.setText("不可取消  预定满意度79%");
            holder.price2.setText("¥500");

        }else{
            hotelData hotel = list.get(position-3);
            holder.img.setImageResource(hotel.getImg());
            holder.name.setText(hotel.getName());
            holder.info.setText(hotel.getInfo());
            holder.sale.setText(hotel.getSale());
            holder.price.setText(hotel.getPrice());
            holder.promotion.setText(hotel.getPromotion());
        }
    }

    private DatePicker findDatePicker(ViewGroup decorView) {
            if(decorView != null){
                for(int i=0,j=decorView.getChildCount();i<j;i++){
                    View child = decorView.getChildAt(i);
                    if(child instanceof DatePicker){
                        return (DatePicker)child;
                    }else if (child instanceof ViewGroup){
                        DatePicker result = findDatePicker((ViewGroup)child);
                        if(result !=null)
                            return result;
                    }
                }
            }
            return null;

    }


    @Override
    public int getItemCount() {
        return list.size()+3;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return type_top;
        else if(position == 1) return type_time;
        else if(position == 2) return type_recommend;
        else return position;
    }
}
