package template.location;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.taolibrary.R;

/**
 * 文件作者：余涛
 * 功能描述：定位模块
 * 创建时间：2019/11/18 13:58
 */
public class LocationUtils {
    private Context mContext;


    private MyLocationListener myListener = new MyLocationListener();
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
    //原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private OnLocationListener mOnLocationListener;  //监听定位的信息
    private int exactNum = 0;  //精确获取定位数据  取三次定位后的数据

    /*************************定位参数设置**************************/
    /**
     *  设置定位参数
     */
    public LocationClientOption option;
    /**
     *  总设置
     */
    public LocationClient mLocationClient = null;
    /**
     *  是否保持定位(默认false)
     *  false:只定位一次  true:保持一直定位
     */
    public boolean isKeepLocation = false;

    public LocationUtils(Context context) {
        this.mContext = context;
       initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mLocationClient = new LocationClient(mContext.getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数


        option = new LocationClientOption();
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
        option.setCoorType("bd09ll");
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        option.setScanSpan(1000);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
        option.setOpenGps(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setLocationNotify(true);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.setIgnoreKillProcess(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false
        option.SetIgnoreCacheException(false);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
        option.setWifiCacheTimeOut(5*60*1000);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        option.setEnableSimulateGps(false);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setIsNeedAddress(true);

        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.setLocOption(option);
    }

    /**
     * 释放资源
     */
    public void release(){
        mLocationClient.unRegisterLocationListener(myListener);
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        mLocationClient.start();

    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        mLocationClient.stop();
    }

    class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //取街道信息

//            Logger.json(JSON.toJSONString(location));
            if (isKeepLocation) { //保持定位
                mOnLocationListener.onData(location);
            } else {
                //只定位一次
                exactNum++;
                //取第3次定位后的数据
                if (exactNum == 3 ) {
                    mOnLocationListener.onData(location);
                    mLocationClient.stop();
                    exactNum = 0;
                }
            }
        }

    }


    /**
     * 监听定位信息
     */
    public interface OnLocationListener{
        void onData(BDLocation bdLocation);
    }
    public void setOnLocationListener(OnLocationListener onLocationListener) {
        mOnLocationListener = onLocationListener;
    }

    /**
     * 替换map地图上默认的图标
     */
    private void replaceLocationIcon(){
        BaiduMap mBaiduMap = null;
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.t_ic_prompt_warn);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
    }

    /**
     * 添加地图上的标识
     */
    private  void addMark(){
        BaiduMap mBaiduMap = null;
        BitmapDescriptor bitmapSelected = BitmapDescriptorFactory.fromResource(R.mipmap.t_ic_prompt_warn);
        LatLng latLng = new LatLng(31.271277,120.74471 );
        OverlayOptions option = (new MarkerOptions()).position(latLng).icon(bitmapSelected);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.clear();
    }

    /**
     * 在mapview上定位当输入的轨迹点
     */
    public void locationMap(BaiduMap baiduMap, BDLocation bdLocation){
        baiduMap.setMyLocationEnabled(true);
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius()) // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(bdLocation.getDirection())
                .latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude())
                .build();
        baiduMap.setMyLocationData(locData);  //设置地图位置
        LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng).zoom(18.0f);
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }


}
