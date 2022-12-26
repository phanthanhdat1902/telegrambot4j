package com.mobicast.telegram4j.utils;

import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.log4j.Log4j2;
import okhttp3.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Log4j2
public class RequestUtil {
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .callTimeout(5, TimeUnit.MINUTES).build();
    public static String post(String url, String bodyRequest) {
        try {
            log.info("==== URL : " + url);
            log.info("=== REQUEST : " + bodyRequest);
            RequestBody body = RequestBody.create(MEDIA_TYPE, bodyRequest);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response execute = client.newCall(request).execute();
            String response = new String(execute.body().bytes(), StandardCharsets.UTF_8);
            log.info("==== RESPONSE : " + response);
            execute.close();
            return response;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String get(String url, List<String> pathSegment, HashMap<String, String> queryParameter) {
        try{
            HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                    .scheme("https")
                    .host(url);
            for (String segment : pathSegment) {
                urlBuilder.addPathSegment(segment);
            }
            for (Map.Entry<String, String> entry : queryParameter.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .get()
                    .build();
            client.newCall(request).execute();
        }catch (Exception e){
            log.error(e.toString());
        }
        return url;
    }
}
