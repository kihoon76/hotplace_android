package com.hotplace25.mobile.utils;

import com.google.gson.Gson;
import com.hotplace25.mobile.exceptions.HttpManagerException;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

/**
 * Created by khnam on 2018-01-10.
 */

public class HttpManager {
    private HttpURLConnection client;

    private static final int CONN_TIMEOUT = 5000;
    private static final int DEFAULT_RETRY_COUNT = 0;
    private static final int RETRY_DELAY_MS = 2000;
    private static final String POST = "POST";
    private static final String GET = "GET";

    private static final String TAG = "HttpManager";
    private int retryCount = DEFAULT_RETRY_COUNT;

    public HttpManager() {
        this(DEFAULT_RETRY_COUNT);
    }

    public HttpManager(int retryCount) {
         this.retryCount = retryCount;
    }

    private String process(String httpStr, String method, String params) throws HttpManagerException {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = "";
        boolean connected = false;

        try {
            url = new URL(httpStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(CONN_TIMEOUT);
            urlConnection.setRequestMethod(method);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream wr = null;
            for(int retry=0; retry<=retryCount && !connected; retry++) {
                if(retry > 0) {
                    Log.i(TAG, "retry count : " +  retry);
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    }
                    catch(InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }

                if("POST".equals(method)) {
                    if(params != null) {
                        wr = new DataOutputStream(urlConnection.getOutputStream());
                        wr.writeBytes(params);
                        wr.flush();
                    }
                }


                switch(urlConnection.getResponseCode()) {
                    case HttpURLConnection.HTTP_OK :
                        connected = true;
                        result = getResponse(urlConnection, wr);
                        break;
                    case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    case HttpURLConnection.HTTP_UNAVAILABLE:
                        if(wr != null) wr.close();
                        break;

                }

            }

        }
        catch (MalformedURLException e) {
            throw new HttpManagerException(e.getMessage());
        }
        catch (IOException e) {
            throw new HttpManagerException(e.getMessage());
        }
        finally {
            if(connected) urlConnection.disconnect();
        }

        return result;
    }

    private String getResponse(HttpURLConnection urlConnection,  DataOutputStream wr) throws IOException  {
        int responseCode = urlConnection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        if(wr != null) wr.close();
        in.close();

        return response.toString();
    }

    public String doGet(String httpStr)  throws HttpManagerException {
        return process(httpStr, "GET", null);
    }

    public String doPost(String httpStr, final String params) throws HttpManagerException {
        return process(httpStr, "POST",  params);
    }

    public <T> T doGetJson(String uri, Class<T> voType) throws HttpManagerException {
        return new Gson().fromJson(doGet(uri), voType);
    }

    public <T> T doPostJson(String uri, String params, Class<T> voType) throws HttpManagerException {
        return new Gson().fromJson(doPost(uri, params), voType);
    }

















}
