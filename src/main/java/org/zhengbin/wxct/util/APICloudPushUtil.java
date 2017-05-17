package org.zhengbin.wxct.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.zhengbin.snowflake.framework.util.HttpClientUtil;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.model.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * APICloud 推送工具类
 * Created by zhengbinMac on 2017/5/10.
 */
public class APICloudPushUtil {
    // 请求路径
    private static final String URL = "https://p.apicloud.com/api/push/message";
    // 应用 ID
    private static final String APPID = "A6949744508643";
    // 应用 KEY
    private static final String APPKEY = "5A06C422-7C29-E2DF-02DA-23FC5BD94DBD";

    public static boolean pushPost(Values values) {
        // 当前时间毫秒数
        long nowMillis = System.currentTimeMillis();

        // AppKey= SHA1（你的应用ID + 'UZ' + 你的应用KEY +'UZ' +当前时间毫秒数）.当前时间毫秒数
        String sh1AppKey = DigestUtils.sha1Hex(APPID + "UZ" + APPKEY + "UZ" + nowMillis) + "." + nowMillis;

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-APICloud-AppId", APPID);
        headers.put("X-APICloud-AppKey", sh1AppKey);
        headers.put("Content-Type", "application/json;charset=utf-8");

        String outStr = JsonUtil.toJson(values);

        String responseJson = HttpClientUtil.doPostStr(URL, outStr, headers);

        Map jsonMap = JsonUtil.jsonToMap(responseJson);

        if ((String.valueOf(jsonMap.get("status"))).equals("1")) {
            return true;
        }else {
            return false;
        }
    }

}
