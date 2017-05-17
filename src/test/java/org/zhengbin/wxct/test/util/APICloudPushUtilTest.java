package org.zhengbin.wxct.test.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.model.Values;
import org.zhengbin.wxct.util.APICloudPushUtil;

import java.util.Map;

/**
 * Created by zhengbinMac on 2017/5/10.
 */
public class APICloudPushUtilTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(APICloudPushUtilTest.class);


    @Test
    public void testMatches() {
//        String path = "post:/apicloud/getAll";
//        if (path.matches(".*:/.*")) {
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }
        int i = 3;
        System.out.println(i % 3);
    }

    @Test
    public void testPostPush() {
        Values v = new Values();
        v.setTitle("这是一条正规的消息");
        v.setContent("这是正规消息的内容");
        v.setType(1);
        v.setPlatform(2);
        LOGGER.debug("result = {}", APICloudPushUtil.pushPost(v));
    }

    @Test
    public void testJson2Map() {
//        ObjectMapper mapper = new ObjectMapper();
        String s = "\"{\"status\":1}\"";
        char[] chars = s.toCharArray();
        chars[0] = ' ';
        chars[chars.length-1] = ' ';
        s = new String(chars);
        Map map = JsonUtil.jsonToMap(s);
        System.out.println("status == " + map.get("status"));
    }

    @Test
    public void test() {
        String json = "{\\n  \\\"status\\\": 1\\n}";
        json = json.replaceAll("\\\\n|\\\\", "");
        System.out.println(json);
    }

}
