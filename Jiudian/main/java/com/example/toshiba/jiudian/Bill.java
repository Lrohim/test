package com.example.toshiba.jiudian;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Toshiba on 2017/12/31.
 */

public class Bill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_bill);

        String pay_condition=getIntent().getStringExtra("pay_condition");
        String room_spent=getIntent().getStringExtra("room_spent");
        String hotel_name=getIntent().getStringExtra("hotel_name");
        String date_in=getIntent().getStringExtra("date_in");
        String date_out=getIntent().getStringExtra("date_out");
        String room_kind=getIntent().getStringExtra("room_kind");
        String room_account=getIntent().getStringExtra("room_account");

        Toolbar toolbar=(Toolbar) findViewById(R.id.bill_toolbar);
        toolbar.setTitle("订单信息");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ((TextView) findViewById(R.id.bill_account)).setText(room_account+"间");
        ((TextView) findViewById(R.id.bill_condition)).setText(pay_condition);
        ((TextView) findViewById(R.id.bill_kind)).setText(room_kind);
        ((TextView) findViewById(R.id.bill_location)).setText(hotel_name);
        ((TextView) findViewById(R.id.bill_spent)).setText(room_spent);
        ((TextView) findViewById(R.id.date_in)).setText(date_in);
        ((TextView) findViewById(R.id.date_out)).setText(date_out);

    }
}
