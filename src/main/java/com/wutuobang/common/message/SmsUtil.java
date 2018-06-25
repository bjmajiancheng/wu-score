package com.wutuobang.common.message;

import com.wutuobang.common.message.RequestPair;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SmsUtil {

    private static OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build();

    public static void send(String mobile, String content) throws IOException {
        List<RequestPair> requestPairs = new ArrayList<RequestPair>();
        requestPairs.add(RequestPairBuilder.build("SpCode", "222803"));
        requestPairs.add(RequestPairBuilder.build("LoginName", "taiji"));
        requestPairs.add(RequestPairBuilder.build("Password", "taiji1234"));
        requestPairs.add(RequestPairBuilder.build("MessageContent", content));
        requestPairs.add(RequestPairBuilder.build("UserNumber", mobile));
        requestPairs.add(RequestPairBuilder.build("SerialNumber", System.currentTimeMillis()));
        StringBuilder urlBuilder = new StringBuilder("");
        if (requestPairs != null) {
            for (int i = 0; i < requestPairs.size(); i++) {
                if (i == 0) {
                    urlBuilder.append("?").append(requestPairs.get(i).getKey()).append("=")
                            .append(requestPairs.get(i).getValue());
                } else {
                    urlBuilder.append("&").append(requestPairs.get(i).getKey()).append("=")
                            .append(requestPairs.get(i).getValue());
                }
            }
        }
        String params = urlBuilder.toString().substring(1);
        System.out.println(params);
        Request request = new Request.Builder().url("http://sms.api.ums86.com:8899/sms/Api/Send.do")
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gb2312"), params))
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body() == null ? "[]" : new String(response.body().bytes(), "gbk");
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        send("15321810621", "陈国俊的天津市居住证积分预约已经取消成功！");
    }
}
