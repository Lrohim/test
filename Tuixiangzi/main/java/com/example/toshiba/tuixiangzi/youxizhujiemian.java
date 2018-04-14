package com.example.toshiba.tuixiangzi;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

/**
 * Created by Toshiba on 2017/11/16.
 */

public class youxizhujiemian extends View {

    Paint paint;
    private int aa [][];
    int finish;
    int finishthis;
    int cx;
    int cy;
    int lx;
    int ly;
    int tempArray[][];
    int point;
    SoundPool sp;
    int soundid;
  //  Context context;


    public void guanka(String ka){//关卡编制
        int[][] temp1={
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                {4,3,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,0,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,1,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
        };
        int[][] temp2={
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                {4,3,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,4,4,4,2,2,4},
                {4,2,2,2,2,2,2,2,2,4,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,4,4,2,2,2,2,4},
                {4,2,2,2,2,0,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,4,2,2,2,2,2,2,4},
                {4,2,2,2,2,0,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,1,1,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
        };
        int[][] temp3={
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                {4,3,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,0,2,2,2,2,4,2,2,2,4},
                {4,2,2,2,2,2,2,2,4,4,4,1,2,2,4},
                {4,2,2,2,2,0,2,2,4,2,2,1,2,2,4},
                {4,2,2,2,2,2,2,2,4,4,4,1,2,2,4},
                {4,2,2,2,2,0,2,2,2,2,4,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,4,4,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
        };
        int[][] temp4={
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
                {4,3,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,2,2,4,4,2,4,4,4,2,2,4,4,2,4},
                {4,2,2,4,4,0,4,4,4,0,0,4,4,2,4},
                {4,2,2,1,2,2,2,1,2,2,2,1,2,2,4},
                {4,2,2,2,2,2,2,2,2,2,2,2,2,2,4},
                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
        };
        if(ka.equals("one")){setAa(temp1);finish=1;}
        if(ka.equals("two")){setAa(temp2);finish=2;}
        if(ka.equals("three")){setAa(temp3);finish=3;}
        if(ka.equals("four")){setAa(temp4);finish=4;}
    }

    public youxizhujiemian(Context context) {
        super(context);
     //   this.context=context;
        paint= new Paint();
        paint.setAntiAlias(true);
        aa=new int[11][15];
        cx=0;cy=0;
   //     guanka(ka);
        lx=1;ly=1;
        finish=0;
        finishthis=0;
        point=0;
    }

    public youxizhujiemian(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        paint= new Paint();
     //   this.context=context;
        paint.setAntiAlias(true);
        aa=new int[11][15];
        cx=0;cy=0;
//       guanka(ka);
        lx=1;ly=1;
        finish=0;
        finishthis=0;
        point=0;
 //       target=new Rect(0,0,70,70);
    }

    public youxizhujiemian(Context context, AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        paint= new Paint();
   //     this.context=context;
        paint.setAntiAlias(true);
        aa=new int[11][15];
        cx=0;cy=0;
//        guanka(ka);
        lx=1;ly=1;
        finish=0;
        finishthis=0;
        point=0;
 //       target=new Rect(0,0,70,70);
    }
//    public youxizhujiemian(Context context,int [][] mapArray){
//        super(context);
//        aa=new int[11][15];
//        aa=mapArray;
//    }
    public void setAa(int [][] mapArray){
        aa=mapArray;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        paint.setColor(Color.BLUE);
//        int b=canvas.getWidth();
//        int a =canvas.getHeight();
//        canvas.drawLine(0.0f,a/100,200.0f,0.0f,paint);
//        int sx,sy,fx,fy;
//        for(int i=0;i<=a/70;i++){
//            canvas.drawLine(0,0+i*70,b,0+i*70,paint);
//        }
//        for(int i=0;i<=b/70;i++){
//            canvas.drawLine(0+i*70,0,0+i*70,a,paint);
//        }
        //屏幕自适应
        if(getWidth()<getHeight()){
            //正的不用动
            point=getWidth()/15;
        }else if(getWidth()>getHeight()){
            point=getHeight()/15;
            for(int i=0;i<11;i++){
                for(int j=0;j<15;j++){
                    tempArray=new int [15][11];
                    tempArray[i][j]=aa[j][i];
                }
            }
            aa=tempArray;
        }

        Bitmap map1= BitmapFactory.decodeResource(getResources(),R.drawable.box);
        Bitmap map2= BitmapFactory.decodeResource(getResources(),R.drawable.destination);
        Bitmap map3= BitmapFactory.decodeResource(getResources(),R.drawable.floor);
        Bitmap map= BitmapFactory.decodeResource(getResources(),R.drawable.man);
        Bitmap map4=BitmapFactory.decodeResource(getResources(),R.drawable.war);
        Bitmap map5=BitmapFactory.decodeResource(getResources(),R.drawable.over);
        sp=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundid=sp.load(getContext(),R.raw.tauyu02,1);

        for(int i=0;i<11;i++){
            for (int j=0;j<15;j++){
                if(aa[i][j] == 0){
                    Rect src=new Rect(0,0,map1.getWidth(),map1.getHeight());
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    canvas.drawBitmap(map1,src,target,paint);
                }
                if(aa[i][j] == 1){
                    Rect src=new Rect(0,0,map2.getWidth(),map2.getHeight());
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    canvas.drawBitmap(map2,src,target,paint);
                }
                if(aa[i][j] == 2){

                    Rect src=new Rect(0,0,map3.getWidth(),map3.getHeight());
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    canvas.drawBitmap(map3,src,target,paint);
                }
                if(aa[i][j] == 3){
//                    lx=i;ly=j;
                    Rect src=new Rect(0,0,map.getWidth(),map.getHeight());
       //             Rect target=new Rect(cx+70*j,cy+70*i,cx+70*j+70,cy+70*i+70);
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    cx=point*j;
                    cy=point*i;
                    canvas.drawBitmap(map,src,target,paint);//方位
                }
                if(aa[i][j] == 4){

                    Rect src=new Rect(0,0,map4.getWidth(),map4.getHeight());
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    canvas.drawBitmap(map4,src,target,paint);
                }
                if(aa[i][j] == 5){

                    Rect src=new Rect(0,0,map5.getWidth(),map5.getHeight());
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    canvas.drawBitmap(map5,src,target,paint);
                    finishthis++;
                }
                if(aa[i][j] == 6){//特殊位置，这里是脚踩笼子
//                    Bitmap map= BitmapFactory.decodeResource(getResources(),R.drawable.man);
                    Rect src=new Rect(0,0,map.getWidth(),map.getHeight());
                    //             Rect target=new Rect(cx+70*j,cy+70*i,cx+70*j+70,cy+70*i+70);
                    Rect target=new Rect(point*j,point*i,(point*j)+point,(point*i)+point);
                    cx=point*j;
                    cy=point*i;
                    canvas.drawBitmap(map,src,target,paint);//方位
                }
            }
        }
        //粗糙获胜
        if(finish==finishthis){
            ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(this,"scaleX",1.0f,0.0f);
            ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(this,"scaleY",1.0f,0.0f);
            ObjectAnimator objectAnimator3=ObjectAnimator.ofFloat(this,"rotation",0.0f,360f);
            objectAnimator1.setDuration(3000);
            objectAnimator2.setDuration(3000);
            objectAnimator3.setDuration(3000);
            objectAnimator1.setRepeatMode(Animation.REVERSE);
            objectAnimator2.setRepeatMode(Animation.REVERSE);
            objectAnimator3.setRepeatMode(Animation.REVERSE);
            objectAnimator1.setRepeatCount(0);
            objectAnimator2.setRepeatCount(0);
            objectAnimator3.setRepeatCount(3);
            objectAnimator1.start();
            objectAnimator2.start();
            objectAnimator3.start();
            objectAnimator1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    getContext().startActivity(new Intent(getContext(),xuanguan.class));
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
          //  Toast.makeText(getContext(),"就是很棒棒",Toast.LENGTH_SHORT).show();
        }else {
            finishthis=0;
        }
        //获胜烟花Animation

//        Bitmap map= BitmapFactory.decodeResource(getResources(),R.drawable.box);
//        Rect src=new Rect(0,0,map.getWidth(),map.getHeight());
//        Rect target=new Rect(0,0,70,70);
//        canvas.drawBitmap(map,src,target,paint);
    }

//    public void drawMap(Canvas canvas, Bitmap bitmap,int x,int y,int px,int py){
//        Rect yuan=new Rect();
//        Rect mu=new Rect();
//
//        yuan.left=
//    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()!=MotionEvent.ACTION_BUTTON_PRESS){

            int ex=(int)event.getX();
            int ey=(int)event.getY();
            int temp;
        //    int temp2;

            Rect [] r=getSurround();
            for(int i=0;i<r.length;i++) {
                if (r[i].contains(ex, ey)) {
//左上右下
                    switch (i){
                        case 0:
                            if(aa[lx][ly-1]==2){
                                if(aa[lx][ly]==6){
                                    cx = cx -point;
                                    aa[lx][ly-1]=3;
                                    aa[lx][ly]=1;
                                    ly--;
                                    music();
                                }
                                else if(aa[lx][ly]==3) {
                                    cx = cx - point;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx][ly - 1];
                                    aa[lx][ly - 1] = temp;
                                    ly--;
                                    music();
                                }
                            }
                            else if(aa[lx][ly-1]==0) {
                                if (aa[lx][ly - 2] == 2) {
                                    if(aa[lx][ly]==3){
                                    cx = cx - point;
                                    temp = aa[lx][ly - 1];
                                    aa[lx][ly - 1] = aa[lx][ly - 2];
                                    aa[lx][ly - 2] = temp;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx][ly - 1];
                                    aa[lx][ly - 1] = temp;
                                    ly--;
                                        music();
                                    }
                                    else if(aa[lx][ly]==6){
                                        cx=cx-point;
                                        aa[lx][ly-2]=0;
                                        aa[lx][ly-1]=3;
                                        aa[lx][ly]=1;
                                        ly--;
                                        music();
                                    }
                                } else if (aa[lx][ly - 2] == 4) {
                                    //不动
                                } else if(aa[lx][ly-2]==1){
                                //成功判断,箱子图片变换,画个新的东西上去
                                    cx=cx-point;
                                    aa[lx][ly-2]=5;
                                    aa[lx][ly-1]=aa[lx][ly];
                                    aa[lx][ly]=2;
                                    ly--;
                                    music();
                                }
                    }
                    else if(aa[lx][ly-1]==4){
                        //不动,别穿墙
                    }else if(aa[lx][ly-1]==1) {
                        //覆盖
                                cx = cx-point;
                                aa[lx][ly]= 2;
                                aa[lx][ly-1]=6;
                                        ly--;
                                music();
                     }else if(aa[lx][ly-1]==5){
                                if(aa[lx][ly-2] == 2 ) {
                                    if(aa[lx][ly]==3) {
                                        cx = cx - point;
                                        aa[lx][ly] = 2;
                                        aa[lx][ly - 2] = 0;
                                        aa[lx][ly - 1] = 6;
                                        ly--;
                                        music();
                                    }else if(aa[lx][ly]==6){
                                        cx = cx - point;
                                        aa[lx][ly] = 1;
                                        aa[lx][ly - 2] = 0;
                                        aa[lx][ly - 1] = 6;
                                        ly--;
                                        music();
                                    }
                                }else if(aa[lx][ly-2] == 1){
                                    if(aa[lx][ly]==3) {
                                        cx = cx - point;
                                        aa[lx][ly] = 2;
                                        aa[lx][ly - 1] = 6;
                                        aa[lx][ly - 2] = 5;
                                        ly--;
                                        music();
                                    }else if(aa[lx][ly]==6){
                                        cx = cx - point;
                                        aa[lx][ly] = 1;
                                        aa[lx][ly - 1] = 6;
                                        aa[lx][ly - 2] = 5;
                                        ly--;
                                        music();
                                    }
                                }
                                //其他情况不动
                            }
                     //       Toast.makeText(getContext(),ly,Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
                            if(aa[lx-1][ly]==2){
                                if(aa[lx][ly]==6){
                                    cy = cy -point;
                                    aa[lx-1][ly]=3;
                                    aa[lx][ly]=1;
                                    lx--;
                                }
                                else if(aa[lx][ly]==3) {
                                    cy = cy - point;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx-1][ly];
                                    aa[lx- 1][ly ] = temp;
                                    lx--;
                                }
                            }
                            else if(aa[lx-1][ly]==0) {
                                if (aa[lx- 2][ly ] == 2) {
                                    if(aa[lx][ly]==3){
                                    cy = cy - point;
                                    temp = aa[lx- 1][ly ];
                                    aa[lx- 1][ly ] = aa[lx- 2][ly ];
                                    aa[lx- 2][ly ] = temp;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx- 1][ly ];
                                    aa[lx- 1][ly ] = temp;
                                    lx--;}
                                    else if(aa[lx][ly]==6){
                                        cy = cy - point;
                                        aa[lx-2][ly]=0;
                                        aa[lx-1][ly]=3;
                                        aa[lx][ly]=1;
                                        lx--;
                                    }
                                } else if (aa[lx- 2][ly ] == 4) {
                                    //不动
                                } else if(aa[lx-2][ly]==1){
                                    //成功判断,箱子图片变换,画个新的东西上去
                                    cy=cy-point;
                                    aa[lx-2][ly]=5;
                                    aa[lx-1][ly]=aa[lx][ly];
                                    aa[lx][ly]=2;
                                    lx--;
                                }
                            }
                            else if(aa[lx-1][ly]==4){
                                //不动,别穿墙
                            }else if(aa[lx-1][ly]==1) {
                                //覆盖
                                cy = cy-point;
                                aa[lx][ly]= 2;
                                aa[lx][ly-1]=6;
                                lx--;
                            }else if(aa[lx-1][ly]==5){
                                if(aa[lx-2][ly] == 2 ) {
                                    if(aa[lx][ly]==3) {
                                        cy = cy - point;
                                        aa[lx][ly] = 2;
                                        aa[lx-2][ly] = 0;
                                        aa[lx-1][ly] = 6;
                                        lx--;
                                    }else if(aa[lx][ly]==6){
                                        cy = cy - point;
                                        aa[lx][ly] = 1;
                                        aa[lx-2][ly] = 0;
                                        aa[lx-1][ly] = 6;
                                        lx--;
                                    }
                                }else if(aa[lx-2][ly] == 1){
                                    if(aa[lx][ly]==3) {
                                        cy = cy - point;
                                        aa[lx][ly] = 2;
                                        aa[lx-1][ly] = 6;
                                        aa[lx-2][ly] = 5;
                                        lx--;
                                    }else if(aa[lx][ly]==6){
                                        cy = cy - point;
                                        aa[lx][ly] = 1;
                                        aa[lx-1][ly] = 6;
                                        aa[lx-2][ly] = 5;
                                        lx--;
                                    }
                                }
                                //其他情况不动
                            }
                            //       Toast.makeText(getContext(),ly,Toast.LENGTH_SHORT).show();
                            break;

                        case 2:
                            if(aa[lx][ly+1]==2){
                                if(aa[lx][ly]==6){
                                    cx = cx +point;
                                    aa[lx][ly+1]=3;
                                    aa[lx][ly]=1;
                                    ly++;
                                }
                                else if(aa[lx][ly]==3) {
                                    cx = cx + point;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx][ly + 1];
                                    aa[lx][ly + 1] = temp;
                                    ly++;
                                }
                            }
                            else if(aa[lx][ly+1]==0) {
                                if (aa[lx][ly + 2] == 2) {
                                    if(aa[lx][ly]==3){
                                    cx = cx + point;
                                    temp = aa[lx][ly + 1];
                                    aa[lx][ly + 1] = aa[lx][ly + 2];
                                    aa[lx][ly + 2] = temp;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx][ly+ 1];
                                    aa[lx][ly + 1] = temp;
                                    ly++;}
                                else if(aa[lx][ly]==6){
                                        cx = cx +point;
                                    aa[lx][ly+2]=0;
                                    aa[lx][ly+1]=3;
                                    aa[lx][ly]=1;
                                        ly++;
                                }
                                } else if (aa[lx][ly + 2] == 4) {
                                    //不动
                                } else if(aa[lx][ly+2]==1){
                                    //成功判断,箱子图片变换,画个新的东西上去
                                    cx=cx+point;
                                    aa[lx][ly+2]=5;
                                    aa[lx][ly+1]=aa[lx][ly];
                                    aa[lx][ly]=2;
                                    ly++;
                                }
                            }
                            else if(aa[lx][ly+1]==4){
                                //不动,别穿墙
                            }else if(aa[lx][ly+1]==1) {
                                //覆盖
                                cx = cx+point;
                                aa[lx][ly]= 2;
                                aa[lx][ly+1]=6;
                                ly++;
                            }else if(aa[lx][ly+1]==5){
                                if(aa[lx][ly+2] == 2 ) {
                                    if(aa[lx][ly]==3) {
                                        cx = cx +point;
                                        aa[lx][ly] = 2;
                                        aa[lx][ly + 2] = 0;
                                        aa[lx][ly + 1] = 6;
                                        ly++;
                                    }else if(aa[lx][ly]==6){
                                        cx = cx +point;
                                        aa[lx][ly] = 1;
                                        aa[lx][ly + 2] = 0;
                                        aa[lx][ly + 1] = 6;
                                        ly++;
                                    }
                                }else if(aa[lx][ly+2] == 1){
                                    if(aa[lx][ly]==3) {
                                        cx = cx + point;
                                        aa[lx][ly] = 2;
                                        aa[lx][ly + 1] = 6;
                                        aa[lx][ly + 2] = 5;
                                        ly++;
                                    }else if(aa[lx][ly]==6){
                                        cx = cx + point;
                                        aa[lx][ly] = 1;
                                        aa[lx][ly + 1] = 6;
                                        aa[lx][ly + 2] = 5;
                                        ly++;
                                    }
                                }
                                //其他情况不动
                            }
                            //       Toast.makeText(getContext(),ly,Toast.LENGTH_SHORT).show();
                            break;

                        case 3:
                            if(aa[lx+1][ly]==2){
                                if(aa[lx][ly]==6){
                                    cy = cy +point;
                                    aa[lx+1][ly]=3;
                                    aa[lx][ly]=1;
                                    lx++;
                                }
                                else if(aa[lx][ly]==3) {
                                    cy = cy + point;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx+ 1][ly ];
                                    aa[lx+ 1][ly ] = temp;
                                    lx++;
                                }
                            }
                            else if(aa[lx+1][ly]==0) {
                                if (aa[lx+ 2][ly ] == 2) {
                                    if(aa[lx][ly]==3){
                                    cy = cy + point;
                                    temp = aa[lx+ 1][ly ];
                                    aa[lx + 1][ly] = aa[lx+ 2][ly ];
                                    aa[lx+ 2][ly ] = temp;
                                    temp = aa[lx][ly];
                                    aa[lx][ly] = aa[lx+ 1][ly];
                                    aa[lx + 1][ly] = temp;
                                    lx++;}
                                else if(aa[lx][ly]==6){
                                        cy = cy + point;
                                    aa[lx+2][ly]=0;
                                    aa[lx+1][ly]=3;
                                    aa[lx][ly]=1;
                                        lx++;
                                }
                                } else if (aa[lx + 2][ly] == 4) {
                                    //不动
                                } else if(aa[lx+2][ly]==1){
                                    //成功判断,箱子图片变换,画个新的东西上去
                                    cy=cy+point;
                                    aa[lx+2][ly]=5;
                                    aa[lx+1][ly]=aa[lx][ly];
                                    aa[lx][ly]=2;
                                    lx++;
                                }
                            }
                            else if(aa[lx+1][ly]==4){
                                //不动,别穿墙
                            }else if(aa[lx+1][ly]==1) {
                                //覆盖
                                cy = cy+point;
                                aa[lx][ly]= 2;
                                aa[lx+1][ly]=6;
                                lx++;
                            }else if(aa[lx+1][ly]==5){
                                if(aa[lx+2][ly] == 2 ) {
                                    if(aa[lx][ly]==3) {
                                        cy = cy +point;
                                        aa[lx][ly] = 2;
                                        aa[lx+2][ly] = 0;
                                        aa[lx+1][ly] = 6;
                                        lx++;
                                    }else if(aa[lx][ly]==6){
                                        cy = cy + point;
                                        aa[lx][ly] = 1;
                                        aa[lx+2][ly] = 0;
                                        aa[lx+1][ly] = 6;
                                        lx++;
                                    }
                                }else if(aa[lx+2][ly] == 1){
                                    if(aa[lx][ly]==3) {
                                        cy = cy + point;
                                        aa[lx][ly] = 2;
                                        aa[lx+1][ly] = 6;
                                        aa[lx+2][ly] = 5;
                                        lx++;
                                    }else if(aa[lx][ly]==6){
                                        cy = cy + point;
                                        aa[lx][ly] = 1;
                                        aa[lx+1][ly] = 6;
                                        aa[lx+2][ly] = 5;
                                        lx++;
                                    }
                                }
                                //其他情况不动
                            }
                            //       Toast.makeText(getContext(),ly,Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            }
        }
        postInvalidate();
        return true;
    }

    public Rect[] getSurround(){
        Rect[] t=new Rect[4];
        t[0]=new Rect(cx-point,cy,cx,cy+point);//乱起八澡
        t[1]=new Rect(cx,cy-point,cx+point,cy);
        t[2]=new Rect(cx+point,cy,cx+point*2,cy+point);
        t[3]=new Rect(cx,cy+point,cx+point,cy+point*2);
        return  t;
    }

    public void music(){
        sp.play(soundid,0.1f,0.1f,1,1,1.0f);//音乐，左声道，右声道，优先级，重复次数，速度
    }


}

