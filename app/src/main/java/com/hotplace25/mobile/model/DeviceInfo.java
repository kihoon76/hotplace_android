package com.hotplace25.mobile.model;

import android.os.Build;

/**
 * Created by khnam on 2018-01-24.
 */

public class DeviceInfo {

    private String deviceUniqueNum;
    private String manufacturer;
    private String model;

    public DeviceInfo() {}

    public DeviceInfo(String deviceUniqueNum) {
        setDeviceUniqueNum(deviceUniqueNum);
        setModel(Build.MODEL);
        setManufacturer(Build.MANUFACTURER);
    }

    public String getDeviceUniqueNum() {
        return deviceUniqueNum;
    }

    public void setDeviceUniqueNum(String deviceUniqueNum) {
        this.deviceUniqueNum = deviceUniqueNum;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "deviceUniqueNum=" + deviceUniqueNum + "&" +
                "manufacturer=" + manufacturer + "&" +
                "model=" + model + "&";
    }
}
