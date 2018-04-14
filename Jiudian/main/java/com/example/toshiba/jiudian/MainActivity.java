package com.example.toshiba.jiudian;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<ImageView> imageViews;
    LinearLayout linearLayout;
    TextView date_in;
    TextView date_out;
    int pre;
    int year1=0,month1=0,day1=0;

    private Handler handler=new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 0:
                    // 得到当前页面的索引
                    int currentItem = viewPager.getCurrentItem();
                    // 要显示的下一个页面的索引
                    currentItem++;
                    // 设置ViewPager显示的页面
                    viewPager.setCurrentItem(currentItem % imageViews.size());
                    break;
                default:
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ini();

        viewPager=(ViewPager) findViewById(R.id.scrollview);
        linearLayout=(LinearLayout) findViewById(R.id.point);
        imageViews=new ArrayList<>();

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ImageView temp1=new ImageView(MainActivity.this);
        ImageView temp2=new ImageView(MainActivity.this);
        ImageView temp3=new ImageView(MainActivity.this);
        ImageView temp4=new ImageView(MainActivity.this);

        temp1.setImageResource(R.drawable.one);
        temp2.setImageResource(R.drawable.two);
        temp3.setImageResource(R.drawable.three);
        temp4.setImageResource(R.drawable.four);

        imageViews.add(temp1);
        imageViews.add(temp2);
        imageViews.add(temp3);
        imageViews.add(temp4);

        for(int i=0;i<imageViews.size();i++) {
            final View view = new View(MainActivity.this);
            view.setBackgroundResource(R.drawable.green);
            DisplayMetrics metrics = new DisplayMetrics();
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 30, metrics);
            float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 30, metrics);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) width, (int) height);
            params.leftMargin = 5;
            view.setLayoutParams(params);
            linearLayout.addView(view);
        }
        linearLayout.getChildAt(0).setBackgroundResource(R.drawable.white);
        MyPagerAdapt myPagerAdapt=new MyPagerAdapt(imageViews);
        viewPager.setAdapter(myPagerAdapt);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                linearLayout.getChildAt(pre).setBackgroundResource(R.drawable.green);
                linearLayout.getChildAt(position).setBackgroundResource(R.drawable.white);
                pre=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();

        ((Button) findViewById(R.id.select)).setBackgroundColor(Color.parseColor("#FFBA00"));
        ((Button) findViewById(R.id.select)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1=new Intent(MainActivity.this,MainActivity1.class);
                startActivity(it1);
            }
        });

        ((Button) findViewById(R.id.record)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2=new Intent(MainActivity.this,Record.class);
                startActivity(it2);
            }
        });

        date_in=(TextView) findViewById(R.id.show_in);
        date_out=(TextView) findViewById(R.id.show_out);

        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        year1=year;month1=month;day1=dayOfMonth;
                        date_in.setText((month+1)+"月"+dayOfMonth+"日");
                    }
                },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        year1=year;month1=month;day1=dayOfMonth;
                        date_out.setText((month+1)+"月"+dayOfMonth+"日");
                    }
                },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        String temp=sharedPreferences.getString("location",null);
        if(temp!=null){
            ((TextView) findViewById(R.id.show_location)).setText(temp);
        }
    }

    public void ini(){
        LitePal.getDatabase();

        if(DBrecord.findAll(DBrecord.class).size()==0) {
            DBhotel dBhotel = new DBhotel();
            dBhotel.setHotel_name("超级大酒店");
            dBhotel.setHotel_core(500);
            dBhotel.setHotel_distance("5000米");
            dBhotel.setHotel_location("广东东软学院旁边");
            dBhotel.setHotel_phone("18024162888");
            dBhotel.save();

            DBrecord dBrecord = new DBrecord();
            dBrecord.setHotel_name(" 超级大酒店");
            dBrecord.setDate_in("1月1日");
            dBrecord.setDate_out("1月2日");
            dBrecord.setPay_condition("待支付");
            dBrecord.setRoom_account(1);
            dBrecord.setRoom_kind("豪华总统套房");
            dBrecord.setRoom_spent(500);
            dBrecord.save();

            DBroom dBroom = new DBroom();
            dBroom.setRoom_kind("豪华总统套房");
            dBroom.setRoom_spent(500);
            dBroom.setRoom_detail("特别舒适");
            dBroom.save();
        }
//        List<DBrecord> dBrecords=dBrecord.findAll(DBrecord.class);
//        Toast.makeText(MainActivity.this,a,Toast.LENGTH_SHORT).show();
    }

}
