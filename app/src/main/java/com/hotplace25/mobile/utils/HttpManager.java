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

    private interface Template {
        Object run(HttpURLConnection urlConnection) throws IOException;
    }

    private Object process(String httpStr, String method, Template t) throws HttpManagerException {
        URL url = null;
        HttpURLConnection urlConnection = null;
        Object result = null;
        boolean connected = false;

        try {
            url = new URL(httpStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(CONN_TIMEOUT);
            urlConnection.setRequestMethod(method);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("User-Agent", "");

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
                urlConnection.connect();
                switch(urlConnection.getResponseCode()) {
                    case HttpURLConnection.HTTP_OK :
                        connected = true;
                        result = t.run(urlConnection);
                        break;
                    case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    case HttpURLConnection.HTTP_UNAVAILABLE:
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

    private String getResponse(HttpURLConnection urlConnection) throws IOException  {
        int responseCode = urlConnection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }

    public String doGet(String httpStr)  throws HttpManagerException {
        return (String)process(httpStr, "GET",  new Template() {

            @Override
            public Object run(HttpURLConnection urlConnection) throws IOException {
                return getResponse(urlConnection);
            }
        });
    }

    public String doPost(String httpStr, final String params) throws HttpManagerException {
        return (String)process(httpStr, "POST",  new Template() {

            @Override
            public Object run(HttpURLConnection urlConnection) throws IOException {
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(params);
                wr.flush();
                wr.close();

                return getResponse(urlConnection);
            }
        });
    }

    public <T> T doGetJson(String uri, Class<T> voType) throws HttpManagerException {
        return new Gson().fromJson(doGet(uri), voType);
    }

    public <T> T doPostJson(String uri, String params, Class<T> voType) throws HttpManagerException {
        return new Gson().fromJson(doPost(uri, params), voType);
    }

















}
