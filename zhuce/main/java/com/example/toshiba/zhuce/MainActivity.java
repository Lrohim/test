package com.example.toshiba.zhuce;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.in)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String name = ((TextView) findViewById(R.id.username)).getText().toString();
                String phonenum = ((TextView) findViewById(R.id.phoneNum)).getText().toString();
                String address = ((TextView) findViewById(R.id.address)).getText().toString();
                String password1 = ((EditText) findViewById(R.id.password1)).getText().toString();
                String password2 = ((EditText) findViewById(R.id.password2)).getText().toString();

                if(!("".equals(name)) && !("".equals(phonenum)) && !("".equals(password1)) && !("".equals(password2)) && !("".equals(address))) {
                    //Toast.makeText(MainActivity.this,name+password1,Toast.LENGTH_SHORT).show();
                    DBOption db=new DBOption();
                    if(db.CheckLiveUser(MainActivity.this,name)){
                        Toast.makeText(MainActivity.this,"您输入的信息有误",Toast.LENGTH_SHORT).show();
                    }else {
                    if (password1.equals(password2)) {
                        db.InsertUserOp(MainActivity.this, name, password1, phonenum, address,0);
                        Intent n = new Intent(MainActivity.this, LogIn.class);
                        startActivity(n);
                    } else {
                        Toast.makeText(MainActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }
                }
        });
        ((Button) findViewById(R.id.out)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w=new Intent(MainActivity.this,MainActivity.class);
                startActivity(w);
            }
        });
    }
}
