package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Toshiba on 2017/9/1.
 */

public class LogIn extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);



        ((Button) findViewById(R.id.go_in)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=((TextView) findViewById(R.id.usern)).getText().toString();
                String password=((TextView) findViewById(R.id.passw)).getText().toString();

                DBOption db=new DBOption();
                if("".equals(username) || "".equals(password)){
                    Toast.makeText(LogIn.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }else {
                    if (db.CheckUser(LogIn.this,username,password)) {
                        //Toast.makeText(LogIn.this, "登陆成功，稍后跳转", Toast.LENGTH_SHORT).show();
                        //UserAttri ADS=db.SelectByUserInf(LogIn.this,username);

                        new DBOption().UpdateByUser(LogIn.this,username,1);

                        Intent p=new Intent(LogIn.this,MyInf.class);
                        //p.putExtra("UserInf",ADS);
                        startActivity(p);
                    } else {
                        Toast.makeText(LogIn.this, "您输入的信息有误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ((Button) findViewById(R.id.go_out)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y=new Intent(LogIn.this,MainActivity.class);
                startActivity(y);
            }
        });
        }
}
