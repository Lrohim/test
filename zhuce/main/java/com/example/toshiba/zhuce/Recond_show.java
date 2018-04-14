package com.example.toshiba.zhuce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Toshiba on 2017/9/7.
 */

public class Recond_show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recond_show);

        RecondAttri r=(RecondAttri) getIntent().getSerializableExtra("Recond");

        ((TextView) findViewById(R.id.Recond_id)).setText(r.getRecordId());
        ((TextView) findViewById(R.id.Recond_time)).setText(r.getRecordDate());
        ((TextView) findViewById(R.id.Recond_goodsname)).setText(r.getGoodsName());
        ((TextView) findViewById(R.id.Recond_sum)).setText(""+r.getSumPrice());

    }
}
