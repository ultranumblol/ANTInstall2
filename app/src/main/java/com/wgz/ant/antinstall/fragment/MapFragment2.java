package com.wgz.ant.antinstall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.wgz.ant.antinstall.R;
import com.wgz.ant.antinstall.overlayutil.DrivingRouteOverlay;
import com.wgz.ant.antinstall.overlayutil.OverlayManager;
import com.wgz.ant.antinstall.overlayutil.TransitRouteOverlay;
import com.wgz.ant.antinstall.overlayutil.WalkingRouteOverlay;

/**
 * Created by qwerr on 2015/11/19.
 */
public class MapFragment2 extends Fragment implements BaiduMap.OnMapClickListener,
        OnGetRoutePlanResultListener {

    //浏览路线节点相关
    private TextView drive,walk,transit;
    int nodeIndex = -1;//节点索引,供浏览节点时使用
    RouteLine route = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    EditText editSt,editEn;
    //地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
    //如果不处理touch事件，则无需继承，直接使用MapView即可
    MapView mMapView = null;    // 地图View
    BaiduMap mBaidumap = null;
    //搜索相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    //定位相关
    private LocationClient mLocationClient;
    private  MyLocationListener mLocationlistener;
    private  boolean isFirstin = true;
    private double mLatitude,mLongtitude,mLatitude2,mLongtitude2,mLatitude3,mLongtitude3;
    private String myLocation;
    GeoCoder msearch2 = null; // 搜索模块，也可去掉地图模块独立使用
    GeoCoder msearch1 = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maptest, null);
        initview(view);

        return  view;
    }
/*
终点名称转换坐标
 */
    private void GERSearch2(String address){
        msearch2.geocode(new GeoCodeOption().city(
                "成都").address(address));


    }
    /*
起点名称转换坐标
 */
    private void GERSearch1(String address){
        msearch1.geocode(new GeoCodeOption().city(
                "成都").address(address));


    }
    //定位到我的位置
    private void CenterToMyLocation() {
        LatLng latLng = new LatLng(mLatitude,mLongtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaidumap.animateMapStatus(msu);
    }
    private void initLocation() {
        mLocationClient = new LocationClient(getActivity());
        mLocationlistener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationlistener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

   /* @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity().getApplicationContext(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaidumap.clear();
        mBaidumap.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult
                .getLocation()));
        mLatitude2=geoCodeResult.getLocation().latitude;
        mLongtitude2 = geoCodeResult.getLocation().longitude;
        String strInfo = String.format("纬度：%f 经度：%f",
                geoCodeResult.getLocation().latitude, geoCodeResult.getLocation().longitude);
        //Toast.makeText(NewMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity().getApplicationContext(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mBaidumap.clear();
        mBaidumap.addOverlay(new MarkerOptions().position(reverseGeoCodeResult.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka)));
        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(reverseGeoCodeResult
                .getLocation()));
        Toast.makeText(getActivity().getApplicationContext(), reverseGeoCodeResult.getAddress(),
                Toast.LENGTH_LONG).show();
    }*/

    private  class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data =  new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();

            mBaidumap.setMyLocationData(data);
            // mLatitude = 30.67;
            // mLongtitude = 104.06;
            mLatitude = bdLocation.getLatitude();
            mLongtitude =bdLocation.getLongitude();
            myLocation = bdLocation.getAddrStr();
            if (isFirstin){
                //坐标
                // LatLng latLng = new LatLng(30.67,104.06);
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaidumap.animateMapStatus(msu);
                isFirstin = false;
                editSt.setText(myLocation);
//                editEn.setText(getArguments().getString("address"));
//                if (getArguments().getString("ifdaohang")!=null&&getArguments().getString("ifdaohang").equals("true")){
//                    //重置浏览节点的路线数据
//                    route = null;
//                    mBaidumap.clear();
//                    // 处理搜索按钮响应
//                    //设置起终点信息，对于tranist search 来说，城市名无意义
//                    PlanNode stNode = PlanNode.withCityNameAndPlaceName("成都",myLocation);
//                    PlanNode enNode = PlanNode.withCityNameAndPlaceName("成都", editEn.getText().toString());
//                    mSearch.walkingSearch((new WalkingRoutePlanOption())
//                            .from(stNode)
//                            .to(enNode));
//                }
                //Toast.makeText(context,bdLocation.getAddrStr(),Toast.LENGTH_SHORT).show();



            }

        }
    }



    private void initview(View view) {
        //初始化地图
        editSt = (EditText) view.findViewById(R.id.start);
        editEn = (EditText)view. findViewById(R.id.end);
        mMapView = (MapView) view.findViewById(R.id.map);
        // 初始化搜索模块，注册事件监听
        msearch2 = GeoCoder.newInstance();
        msearch1 = GeoCoder.newInstance();
        msearch1.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(getActivity().getApplicationContext(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                mBaidumap.clear();
                mBaidumap.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.icon_marka)));
                mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult
                        .getLocation()));
                mLatitude3=geoCodeResult.getLocation().latitude;
                mLongtitude3 = geoCodeResult.getLocation().longitude;
                String strInfo = String.format("纬度：%f 经度：%f",
                        geoCodeResult.getLocation().latitude, geoCodeResult.getLocation().longitude);
                //Toast.makeText(NewMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        });
        msearch2.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(getActivity().getApplicationContext(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                mBaidumap.clear();
                mBaidumap.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.icon_marka)));
                mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult
                        .getLocation()));
                mLatitude2=geoCodeResult.getLocation().latitude;
                mLongtitude2 = geoCodeResult.getLocation().longitude;
                String strInfo = String.format("纬度：%f 经度：%f",
                        geoCodeResult.getLocation().latitude, geoCodeResult.getLocation().longitude);
                //Toast.makeText(NewMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        });
        editSt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    CenterToMyLocation();

                }
            }
        });
        mBaidumap = mMapView.getMap();
        initLocation();

        //设定中心点坐标
        LatLng cenpt = new LatLng(30.67,104.06);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(12)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);


        walk = (TextView) view.findViewById(R.id.walkway);
        transit = (TextView) view.findViewById(R.id.transit);
        drive = (TextView) view.findViewById(R.id.drive);

        walk.setOnClickListener(new MyonClick());
        transit.setOnClickListener(new MyonClick());
        drive.setOnClickListener(new MyonClick());
        //地图点击事件处理
        mBaidumap.setOnMapClickListener(this);
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    }
    public class MyonClick implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            //重置浏览节点的路线数据
            route = null;
            mBaidumap.clear();
            // 处理搜索按钮响应
            //设置起终点信息，对于tranist search 来说，城市名无意义
            GERSearch1(editSt.getText().toString().trim());
            GERSearch2(editEn.getText().toString().trim());

            LatLng latLng = new LatLng(mLatitude3,mLongtitude3);
            LatLng latLng2 = new LatLng(mLatitude2,mLongtitude2);
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            mBaidumap.animateMapStatus(msu);
            //坐标搜索
            PlanNode stNode = PlanNode.withLocation(latLng);
            PlanNode enNode = PlanNode.withLocation(latLng2);
            String strInfo = String.format("起点坐标纬度：%f 经度：%f",
                    mLatitude, mLongtitude);
            String endInfo = String.format("终点坐标纬度：%f 经度：%f",
                    mLatitude2, mLongtitude2);

            Log.i("baidu", strInfo);
            Log.i("baidu",endInfo);
            //PlanNode stNode = PlanNode.withCityNameAndPlaceName("成都", editSt.getText().toString());
            //PlanNode enNode = PlanNode.withCityNameAndPlaceName("成都", editEn.getText().toString());

            // 实际使用中请对起点终点城市进行正确的设定
            if (v.getId() == R.id.drive) {
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
            } else if (v.getId() == R.id.transit) {
                mSearch.transitSearch((new TransitRoutePlanOption())
                        .from(stNode)
                        .city("成都")
                        .to(enNode));
            } else if (v.getId() == R.id.walkway) {
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
            }
        }
    }
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            // mBtnPre.setVisibility(View.VISIBLE);
            // mBtnNext.setVisibility(View.VISIBLE);
            route = result.getRouteLines().get(0);
            WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaidumap);
            mBaidumap.setOnMarkerClickListener(overlay);
            routeOverlay = overlay;
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {

        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            // mBtnPre.setVisibility(View.VISIBLE);
            // mBtnNext.setVisibility(View.VISIBLE);
            route = result.getRouteLines().get(0);
            TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaidumap);
            mBaidumap.setOnMarkerClickListener(overlay);
            routeOverlay = overlay;
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity().getApplicationContext(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            //mBtnPre.setVisibility(View.VISIBLE);
            // mBtnNext.setVisibility(View.VISIBLE);
            route = result.getRouteLines().get(0);
            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
            routeOverlay = overlay;
            mBaidumap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    //定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    private class MyTransitRouteOverlay extends TransitRouteOverlay {

        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    @Override
    public void onMapClick(LatLng point) {
        mBaidumap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi poi) {
        return false;
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mSearch.destroy();
        mMapView.onDestroy();
        mMapView = null;
        // 退出时销毁定位
        mLocationClient.stop();
        // 关闭定位图层
        mBaidumap.setMyLocationEnabled(false);
        super.onDestroy();
    }

}

