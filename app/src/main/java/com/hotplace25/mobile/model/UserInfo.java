package com.hotplace25.mobile.model;

/**
 * Created by khnam on 2018-01-19.
 */

public class UserInfo {
    private String osKind;
    private String osVersion;
    private String uniqueDeviceNum;
    private String email;

    public String getOsKind() {
        return osKind;
    }

    public void setOsKind(String osKind) {
        this.osKind = osKind;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getUniqueDeviceNum() {
        return uniqueDeviceNum;
    }

    public void setUniqueDeviceNum(String uniqueDeviceNum) {
        this.uniqueDeviceNum = uniqueDeviceNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}