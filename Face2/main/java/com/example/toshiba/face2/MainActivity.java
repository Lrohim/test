package com.example.toshiba.face2;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.common.Config;
import com.example.toshiba.face.R;
import com.youtu.Youtu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button photo;
    Button analyze;
    Button stock;
    Button save;
    ImageView imageView;
    Uri urii;
    File filetemp;
    Bitmap ImageViewPath;
    int i=0;
    List<Myface> list;
    ProgressDialog progressDialog;
//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = (Button) findViewById(R.id.photo);
        analyze = (Button) findViewById(R.id.analyze);
        stock = (Button) findViewById(R.id.stock);
        save = (Button) findViewById(R.id.save);
        imageView=(ImageView) findViewById(R.id.image);
//        progressBar=(ProgressBar) findViewById(R.id.progress);
        list=new ArrayList<>();

        photo.setOnClickListener(this);
        analyze.setOnClickListener(this);
        stock.setOnClickListener(this);
        save.setOnClickListener(this);

        imageView.setImageResource(R.drawable.nice);//test
        ImageViewPath=BitmapFactory.decodeResource(getResources(),R.drawable.nice);
//
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads().detectDiskWrites().detectNetwork()
//                .penaltyLog().build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
//                .penaltyLog().penaltyDeath().build());


//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
    }
    @Override
    public void onClick(View v) {
        Intent it= null;
        switch (v.getId()){
            case R.id.photo:
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)//"picture"+i+".jpg"
                File f=new File(getExternalCacheDir(),"picture.jpg");//之前getderect下面捕捉不到图片,cache是应用缓存目录
                i++;
                filetemp=f;
                try {
                    if(f.exists()){
                    f.delete();
                }
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                f.mkdir();
                if(Build.VERSION.SDK_INT >= 24){
                    urii= FileProvider.getUriForFile(MainActivity.this,"com.example.car.fileprovider",f);
                }else {
                    urii =Uri.fromFile(f);
                }
                it=new Intent("android.media.action.IMAGE_CAPTURE");
                it.putExtra(MediaStore.EXTRA_OUTPUT,urii);
                startActivityForResult(it,1);
                break;
            case R.id.analyze:
//                progressBar.setVisibility(View.VISIBLE);
                progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("等一下呗");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                setAnalyze();
//                drawKuangKuang();
                break;
            case R.id.stock:
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    openStock();
                }
                break;
            case R.id.save:
                //尴尬
                if(filetemp!=null){
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(filetemp);
                intent.setData(uri);
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                this.sendBroadcast(intent);
                }else {
                    Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void drawKuangKuang(){

        if(list.get(0)!=null){
            Bitmap bitmap=ImageViewPath.copy(ImageViewPath.getConfig(),true);//可变
            Canvas canvas=new Canvas(bitmap);
            Paint paint=new Paint();
            Paint paint1=new Paint();
            paint.setColor(Color.RED);
            paint1.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(7);
            paint1.setStrokeWidth(5);
            paint1.setTextSize(60);
//             int a=list.get(0).getHeight();//成功获取
            for(int i=0;i<list.size();i++) {
                canvas.drawRect(list.get(i).getX(),list.get(i).getY(),list.get(i).getX()+list.get(i).getWidth(),list.get(i).getY()+list.get(i).getHeight(),paint);
                canvas.drawText("性别:"+"男"+"年龄"+list.get(i).getAge(),list.get(i).getX(),list.get(i).getY()-20,paint1);
            }
//            return bitmap;
            imageView.setImageBitmap(bitmap);
        }
        imageView.postInvalidate();
    }

    private void setAnalyze(){
        if(ImageViewPath!=null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Youtu youtu = new Youtu(Config.APP_ID, Config.SECRET_ID, Config.SECRET_KEY, Youtu.API_TENCENTYUN_END_POINT);
                        JSONObject jsonObject = youtu.DetectFace(ImageViewPath, 0);//不为空
                        if(jsonObject!=null) {
                            parse(jsonObject.toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            Toast.makeText(MainActivity.this,"空",Toast.LENGTH_SHORT).show();
        }
    }

    private void parse(String data){
        String temp=data.split("\\[")[1];
        data=temp.split("\\]")[0];
        temp="["+data+"]";
//        Toast.makeText(MainActivity.this,temp,Toast.LENGTH_SHORT).show();
        try {
            JSONArray array = new JSONArray(temp);
            for(int i=0;i<array.length();i++){
                JSONObject j=array.getJSONObject(i);
                Myface myface=new Myface();
                myface.setX(j.getInt("x"));
                myface.setY(j.getInt("y"));
                myface.setAge(j.getInt("age"));
                myface.setHeight(j.getInt("height"));
                myface.setWidth(j.getInt("width"));
                myface.setGender(j.getString("gender"));
                list.add(myface);
            }
            drawKuangKuang();
            progressDialog.dismiss();
//            progressBar.setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openStock(){
        Intent it= null;
        it=new Intent("android.intent.action.GET_CONTENT");
        it.setType("image/*");
        startActivityForResult(it,2);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
            switch (requestCode){
                case 1:
                    if(resultCode == RESULT_OK){
                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 4;
                            Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(urii),null,options);
//                            if(bitmap==null) {
//                                Toast.makeText(MainActivity.this, "66666666666", Toast.LENGTH_SHORT).show();
//                            }
                            ImageViewPath=bitmap;
                            imageView.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    if(resultCode == RESULT_OK){
                        if(Build.VERSION.SDK_INT>=19){
                            handleImageOnKitKat(data);
                        }else {
                            handleImageBeforeKitKat(data);
                        }
                    }
                    break;
                default:
                    Toast.makeText(MainActivity.this,"123112313",Toast.LENGTH_SHORT).show();
                    break;
            }
    }

    private void handleImageBeforeKitKat(Intent data) {
            Uri uri=data.getData();
             String imagePath = getImagePath(uri,null);
            displayImage(imagePath);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent date){
            String imagePath = null;
            Uri uri=date.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docID=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docID.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath= getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docID));
                imagePath = getImagePath(contentUri,null);
            }else if("content".equalsIgnoreCase(uri.getScheme())){
                imagePath=getImagePath(uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme())){
                imagePath=uri.getPath();
            }
            displayImage(imagePath);
        }

    }

    private void displayImage(String imagePath) {
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            ImageViewPath=bitmap;
            imageView.setImageBitmap(bitmap);
        }else {
            //没获取到图片
        }
    }

    private String getImagePath(Uri uri,String selection) {
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openStock();
                }else {
            //OJBK
                }
                break;
            default:
//                break;
        }
    }
}
