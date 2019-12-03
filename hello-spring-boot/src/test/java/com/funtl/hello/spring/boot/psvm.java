package com.funtl.hello.spring.boot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.entity.Correspondent;
import com.funtl.hello.spring.boot.util.OkHttpClientUtil;
import com.google.common.collect.Maps;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author qy
 * @date 2019/9/11 17:56
 * @description
 */
public class psvm{

    public static void main(String[] args) throws IOException {
        String url ="http://localhost:28088/nfplus-report-conduct-admin/api/getCorrespondent";
        Map map = Maps.newHashMap();
        map.put("name","小li");
        Response data = OkHttpClientUtil.getInstance().postData(url,map);
        String jsonString = Objects.requireNonNull(data.body()).string();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray array=  jsonObject.getJSONArray("data");
        List<Correspondent> list = JSON.parseArray(JSONArray.toJSONString(array), Correspondent.class);

        System.out.println(list);


    }
}
