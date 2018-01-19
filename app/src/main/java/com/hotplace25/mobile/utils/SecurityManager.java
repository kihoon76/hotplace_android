package com.hotplace25.mobile.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by khnam on 2018-01-19.
 */

public class SecurityManager {
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory()  + "";
    private static final String[] sRootFilesPath;

    static {
        sRootFilesPath = new String[] {
                ROOT_PATH + "/system/bin/su"
                ,ROOT_PATH + "/system/xbin/su"
                ,ROOT_PATH + "/system/app/SuperUser.apk"
                ,ROOT_PATH + "/data/data/com.noshufou.android.su"
        };
    }

    public static boolean isRooting() {
        boolean isRootingFlag = false;

        try {
            Runtime.getRuntime().exec("su");
            isRootingFlag = true;
        }
        catch (IOException e) { }

        if(!isRootingFlag) {
            isRootingFlag = checkRootingFiles(createFiles(sRootFilesPath));
        }

        return isRootingFlag;
    }

    private static boolean checkRootingFiles(File... file) {
        boolean result = false;
        for(File f : file) {
            if(f != null && f.exists() && f.isFile()) {
                result = true;
                break;
            }
            else {
                result = false;
            }
        }

        return result;
    }

    private static File[] createFiles(String[] sFiles) {
        File[] rootingFiles = new File[sFiles.length];
        for(int i=0 ; i < sFiles.length; i++){
            rootingFiles[i] = new File(sFiles[i]);
        }

        return rootingFiles;
    }
}
