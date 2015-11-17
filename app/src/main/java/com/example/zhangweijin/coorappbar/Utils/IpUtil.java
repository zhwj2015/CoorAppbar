package com.example.zhangweijin.coorappbar.Utils;

import com.example.zhangweijin.coorappbar.model.Ip;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by zhangweijin on 15-11-15.
 */
public class IpUtil {
    //eg : http://ip.taobao.com/service/getIpInfo.php?ip=202.202.32.202

    static final String  ENDPOINT = "http://ip.taobao.com/service";
    public interface TaobaoIPService {
        @GET("/getIpInfo.php")
        void getIp(@Query("ip")String ip, Callback<Ip> callback);
    }

    static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    public static TaobaoIPService taobaoIPService = restAdapter.create(TaobaoIPService.class);
}
