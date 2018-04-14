package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Toshiba on 2017/9/4.
 */

public class unlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ((TextView) findViewById(R.id.act_login1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(unlogin.this,LogIn.class);
                startActivity(c);
            }
        });
        ((TextView) findViewById(R.id.acti_login2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(unlogin.this,LogIn.class);
                startActivity(c);
            }
        });
    }
}
