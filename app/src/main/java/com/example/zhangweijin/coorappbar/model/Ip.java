package com.example.zhangweijin.coorappbar.model;

import org.json.JSONObject;

/**
 * Created by zhangweijin on 15-11-15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Ip {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     *
     * @return
     * The code
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}

