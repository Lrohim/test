package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Created by Toshiba on 2017/9/9.
 */

public class showlist_null extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist_null);

        ((Button) findViewById(R.id.go)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(showlist_null.this,LogIn.class);
                startActivity(a);
            }
        });
    }

}
