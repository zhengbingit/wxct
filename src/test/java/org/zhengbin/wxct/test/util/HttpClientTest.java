package org.zhengbin.wxct.test.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.util.HttpClientUtil;
import org.zhengbin.snowflake.framework.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/5/9.
 */
public class HttpClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);

    private static final String URL = "https://p.apicloud.com/api/push/message";
    // 应用 ID
    private static final String APPID = "A6949744508643";
    // 应用 KEY
    private static final String APPKEY = "5A06C422-7C29-E2DF-02DA-23FC5BD94DBD";

    /**
     * APICloud 推送消息功能测试
     */
    @Test
    public void testPost() {
        long nowMillis = System.currentTimeMillis();    // 当前时间毫秒数
        // AppKey= SHA1（你的应用ID + 'UZ' + 你的应用KEY +'UZ' +当前时间毫秒数）.当前时间毫秒数
        String sh1AppKey = DigestUtils.sha1Hex(APPID + "UZ" + APPKEY + "UZ" + nowMillis) + "." + nowMillis;
        LOGGER.debug("sh1AppKey = {}", sh1AppKey);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-APICloud-AppId", "A6949744508643");
        headers.put("X-APICloud-AppKey", sh1AppKey);
        headers.put("Content-Type", "application/json;charset=utf-8");
        values v = new values();
        v.setTitle("你好");
        v.setContent("123asdfasdfasdf");
        v.setType(1);
        v.setPlatform(2);
        String outStr = JsonUtil.toJson(v);
        LOGGER.debug("responseStr = {}", HttpClientUtil.doPostStr(URL, outStr, headers));
    }


    private class values {
        /**
         * "values" : {
         "title" : "消息标题",
         "content" : "消息内容",
         "type" : 1, //– 消息类型，1:消息 2:通知
         "platform" : 0, //0:全部平台，1：ios, 2：android
         //    "groupName" : "department", //推送组名，多个组用英文逗号隔开.默认:全部组。eg.group1,group2 .
         //    "userIds" : "testId" //推送用户id, 多个用户用英文逗号分隔，eg. user1,user2。
         }
         */
        private String title;
        private String content;
        private int type;
        private int platform;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "values{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", type=" + type +
                    ", platform=" + platform +
                    '}';
        }
    }
}
