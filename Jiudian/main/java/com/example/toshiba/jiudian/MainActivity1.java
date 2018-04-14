package com.example.toshiba.jiudian;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity implements PoiSearch.OnPoiSearchListener,AMap.InfoWindowAdapter{

    MapView mapView = null; //地图View
    AMap aMap;  //地图核心控制器
    MyLocationStyle myLocationStyle; //定位点样式
    UiSettings uiSettings;
    View infoWindow = null;
    String citycode;
    double latitude;//纬度
    double longitude;//经度
    private static final int BAIDU_READ_PHONE_STATE =100;    //自定义一个权限获取码，用于回调函数中做对应处理

    //声明AmapLocationClient对象
    AMapLocationClient client = null;
    //声明定位回调监听器
    AMapLocationListener aMapLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    AMapLocationClientOption aMapLocationClientOption = null;

    private EditText et_search;
    private LinearLayout search_result_layout;
    private TextView name,price,info,distance,detail;
    private Button bt_book;
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initView();
        mapView.onCreate(savedInstanceState);
        search_result_layout.setVisibility(View.INVISIBLE);

//        if(MainActivity1.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
//            this.requestPermissions( new String[] { Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
//        }

        ((ImageView) findViewById(R.id.bt_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("location",location);
                editor.apply();
                finish();
            }
        });

        //marker的点击监听器
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //设置搜索结果显示的数据
                showSearchResult(marker);
                return false;
            }
        };

        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setInfoWindowAdapter(this);
            aMap.setOnMarkerClickListener(markerClickListener);
            uiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        }

        //显示定位点
        showLocation();
        //init
        init();

        //设置搜索框的图片
        Drawable search_draw = getResources().getDrawable(R.drawable.search);
        search_draw.setBounds(0,0,search_draw.getIntrinsicWidth()/2,search_draw.getIntrinsicHeight()/2);
        et_search.setCompoundDrawables(search_draw,null,null,null);



        //et_search设置监听
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if ((i == EditorInfo.IME_ACTION_SEND
                        || i == EditorInfo.IME_ACTION_DONE
                        || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()
                        && KeyEvent.ACTION_DOWN == keyEvent.getAction()))&& !et_search.getText().toString().equals("")) {
                    PoiSearch.Query query = new PoiSearch.Query(et_search.getText().toString(), "宾馆酒店", citycode);
                    query.setPageSize(10);//设置每页返回多少条poiItem
                    query.setPageNum(1);//查询页码

                    //构造POISearch对象，并设置监听
                    PoiSearch poisearch = new PoiSearch(MainActivity1.this, query);//初始化POISearch对象
                    poisearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,longitude),8000));
                    poisearch.setOnPoiSearchListener(MainActivity1.this);//设置回调数据的监听器
                    //调用POISearch的searchPOIAsyn()方法发送请求
                    poisearch.searchPOIAsyn();//开始搜索
                }
                return false;
            }
        });

    }

    //显示搜索结果方法
    public void showSearchResult(final Marker marker){
        name.setText(marker.getTitle());
        price.setText("¥500");
        search_result_layout.setVisibility(View.VISIBLE);
        //下订单按钮设置监听
        bt_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this,Book_Activity.class);
                intent.putExtra("name",marker.getTitle());
                startActivity(intent);
            }
        });
        marker.showInfoWindow();
    }
    public void setini(List<PoiItem> poiItems){

        for(int i=0;i<poiItems.size();i++) {
            DBhotel dBhotel = new DBhotel();
            dBhotel.setHotel_name(poiItems.get(i).getTitle());
            dBhotel.setHotel_core(i*66);
            dBhotel.setHotel_distance(poiItems.get(i).getDistance()+"米");
            dBhotel.setHotel_location(poiItems.get(i).getSnippet());
            dBhotel.setHotel_phone(poiItems.get(i).getTel()+"");
            dBhotel.save();

            DBrecord dBrecord = new DBrecord();
            dBrecord.setHotel_name(poiItems.get(i).getTitle());
            dBrecord.setDate_in("1月"+i+"日");
            dBrecord.setDate_out("1月"+(i+5)+"日");
            dBrecord.setPay_condition("待支付");
            dBrecord.setRoom_account(1);
            dBrecord.setRoom_kind("豪华总统套房");
            dBrecord.setRoom_spent(i*66);
            dBrecord.save();

            DBroom dBroom = new DBroom();
            dBroom.setRoom_kind(poiItems.get(i).getTitle());
            dBroom.setRoom_spent(i*66);
            dBroom.setRoom_detail("特别舒适");
            dBroom.save();
        }
    }

    //POI搜索
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if(i==1000 && poiResult!=null) {
            aMap.clear();//每次搜索重新清除所有marker
            search_result_layout.setVisibility(View.INVISIBLE);
            List<PoiItem> items = poiResult.getPois();
            setini(items);

            for (PoiItem item : items) {
                LatLonPoint latLonPoint = item.getLatLonPoint();
                LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
                Log.d("经纬度",latLonPoint.getLatitude()+","+latLonPoint.getLongitude());
                Log.d("titel:",item.getTitle());
                Log.d("AdName:",item.getAdName());
                Log.d("BusinessArea:",item.getBusinessArea());
                Log.d("ParkingType:",item.getParkingType());
                Log.d("Snippet:",item.getSnippet());
                Log.d("Tel:",item.getTel());
                Log.d("TypeDes:",item.getTypeDes());
                Log.d("Distant:",item.getDistance()+"");
                Marker marker = aMap.addMarker(new MarkerOptions().position(latLng)
                        .title(item.getTitle())
                        .snippet(item.getSnippet()));
                marker.showInfoWindow();
                showSearchResult(marker);
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker.getPosition()));
                aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    private void init() {
        //初始化定位
        client = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        client.setLocationListener(aMapLocationListener);
        //初始化AMapLocationClientOption对象
        aMapLocationClientOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果，该方法默认为false
        aMapLocationClientOption.setOnceLocation(false);

        //获取最近3s内精度最高的一次定位结果
        //设置setOnceLocationLatest接口为true，启动定位是sdk会返回最近3s内精度最高的一次定位结果
        aMapLocationClientOption.setOnceLocationLatest(true);
        //设置是否返回地址信息(默认返回地址信息)
        aMapLocationClientOption.setNeedAddress(true);
        //设置是否允许模拟位置，默认为false
        aMapLocationClientOption.setMockEnable(false);
        //关闭缓存机制
        aMapLocationClientOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        client.setLocationOption(aMapLocationClientOption);
        //启动定位
        client.startLocation();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if(infoWindow == null){
            infoWindow = LayoutInflater.from(this)
                    .inflate(R.layout.custom_info_window,null);
        }
        render(marker,infoWindow);

        return infoWindow;
    }

    private void render(Marker marker, View infoWindow) {
        TextView name = (TextView) infoWindow.findViewById(R.id.hotel_name);
        TextView price = (TextView) infoWindow.findViewById(R.id.price);
        TextView score = (TextView) infoWindow.findViewById(R.id.score);
        name.setText(marker.getTitle());
        price.setText("¥299");
        score.setText("4.9分");
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


    private class MyAMapLocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    citycode = aMapLocation.getCityCode();
                    latitude = aMapLocation.getLatitude();
                    longitude = aMapLocation.getLongitude();
//                    String position = aMapLocation.getAddress();
                    location = aMapLocation.getAddress();
                    Log.e("位置：", aMapLocation.getAddress());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.myMap);
        et_search = (EditText) findViewById(R.id.et_search);
        search_result_layout = (LinearLayout) findViewById(R.id.result_search);
        name = (TextView) search_result_layout.findViewById(R.id.name);
        price = (TextView) search_result_layout.findViewById(R.id.price);
        info = (TextView) search_result_layout.findViewById(R.id.info);
        detail = (TextView) search_result_layout.findViewById(R.id.detail);
        bt_book = (Button) search_result_layout.findViewById(R.id.book);
    }

    private void showLocation() {
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;
        myLocationStyle.interval(2000);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        uiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(15);
        aMap.moveCamera(cameraUpdate);
        aMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
//        switch(requestCode) {
//            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
//            caseBAIDU_READ_PHONE_STATE:
//            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
//            } else{
//                // 没有获取到权限，做特殊处理
//            }
//            break;
//            default:
//                break;
//        }
//    }
}

