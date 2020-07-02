package com.funtl.hello.spring.boot.util;

import lombok.Cleanup;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class OkHttpUtils {

    private static Logger log = LoggerFactory.getLogger(OkHttpUtils.class);

    public enum HttpMediaType {
        X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded; charset=utf-8"),
        JSON("application/json; charset=utf-8"),
        TEXT_PLAIN("text/plain");

        HttpMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        private String mediaType;

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }
    }

    /**
     * 以Form参数POST提交
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params) {
        return post(url, params, null);
    }

    /**
     * @Param No such property: code for class: Script1
     * @Description:指定httpclient 请求
     */
    public static String post(OkHttpClient okHttpClient, String url, Map<String, String> params) {
        return post(okHttpClient, url, params, null);
    }


    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        return post(OkHttpBuilder.getDefaultClient(), url, params, headers);
    }

    /**
     * 以Form参数POST提交
     *
     * @param url
     * @param params  form参数键值对
     * @param headers
     * @return
     * @throws IOException
     */
    public static String post(OkHttpClient okHttpClient, String url, Map<String, String> params, Map<String, String> headers) {
        RequestBody paramBody = null;
        if (params != null && !params.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<String, String> entry = iter.next();
                builder.add(entry.getKey(), entry.getValue());
            }
            paramBody = builder.build();
        }

        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                builder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        Request request = builder.url(url).post(paramBody).build();

        return createRequest(okHttpClient, request);
    }


    /**
     * GET提交键值对
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) {

        Request request = new Request.Builder().url(url).get().build();

        return createRequest(request);
    }

    /**
     * 带有以换行符请求体的post请求
     *
     * @param url
     * @param texts   texts中的元素以换行符分割
     * @param headers
     * @return
     * @throws Exception
     */
    public static String post(String url, String[] texts, Map<String, String> headers) {
        StringBuilder pushContent = new StringBuilder();
        for (String text : texts) {
            pushContent.append(text).append("\n");
        }
        return post(url, pushContent.toString(), headers);
    }


    /**
     * 带有请求体的post请求
     *
     * @param url        请求地址
     * @param reqBodyRaw 请求体原始数据
     * @param headers    请求头
     * @return
     * @throws Exception
     */
    public static String post(String url, String reqBodyRaw, Map<String, String> headers) {

        return post(url, reqBodyRaw, headers, HttpMediaType.TEXT_PLAIN);
    }

    /**
     * 带有请求体的post请求
     *
     * @param url            请求地址
     * @param requestBodyRaw 请求体原始数据
     * @param httpMediaType  MediaType
     * @return
     * @throws Exception
     */
    public static String post(String url, String requestBodyRaw, HttpMediaType httpMediaType) {

        return post(url, requestBodyRaw, null, httpMediaType);
    }

    /**
     * 设置header,MediaType,带有请求体的post请求  使用默认的 defaultClient
     *
     * @param url            请求地址
     * @param requestBodyRaw 请求体原始数据
     * @param headers        请求头
     * @param httpMediaType  MediaType
     * @return
     * @throws Exception
     */
    public static String post(String url, String requestBodyRaw, Map<String, String> headers, HttpMediaType httpMediaType) {

        return post(OkHttpBuilder.getDefaultClient(), url, requestBodyRaw, headers, httpMediaType);
    }

    public static String post(OkHttpClient okHttpClient, String url, String requestBodyRaw, Map<String, String> headers, HttpMediaType httpMediaType) {

        //设置请求体
        Request.Builder builder = new Request.Builder();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse(httpMediaType.mediaType);
        RequestBody body = RequestBody.create(MEDIA_TYPE_TEXT, requestBodyRaw);
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                builder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        builder.url(url).post(body);
        Request request = builder.build();

        return createRequest(okHttpClient, request);
    }

    /**
     * 提交表单
     */
    public static String postForm(String url, Map<String, String> bodyMap) {
        FormBody.Builder builder = new FormBody.Builder();
        if (bodyMap != null && bodyMap.size() > 0) {
            for (Map.Entry<String, String> bodyEntry : bodyMap.entrySet()) {
                builder.add(bodyEntry.getKey(), bodyEntry.getValue());
            }
        }
        return createRequest(new Request.Builder().url(url).post(builder.build()).build());
    }


    private static String createRequest(Request request) {
        return createRequest(OkHttpBuilder.getDefaultClient(), request);
    }


    private static String createRequest(OkHttpClient okHttpClient, Request request) {
        try {
            @Cleanup
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                @Cleanup
                ResponseBody body = response.body();
                return body.string();
            } else {
                throw new Exception(new StringBuilder("Unexpected code ").append(response).append(",url:").append(request.url().toString()).toString());
            }
        } catch (IOException e) {
            log.error("{},url={}", e.getMessage(), request.url().toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 是否请求失败
     *
     * @param response
     * @return true: 请求失败 false: 请求成功
     */
    private static boolean responseIsFailed(Response response) throws InterruptedException {
        if (!response.isSuccessful()) {
            if (response != null) {
                response.close();
            }
            Thread.sleep(50);
            return true;
        }
        return false;
    }

    //异步post
    public static void asynPost(final String url, Map<String, Object> params, Map<String, String> headers) {
        RequestBody paramBody = null;
        if (params != null && !params.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Iterator<Map.Entry<String, Object>> iter = params.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<String, Object> entry = iter.next();
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            paramBody = builder.build();
        }
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                builder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        Request request = builder.url(url).post(paramBody).build();
        Call call = OkHttpBuilder.getDefaultClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("请求{}出错", url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("请求{}成功", url);
            }
        });
    }

}