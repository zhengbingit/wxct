package org.zhengbin.wxct.test.util;

import org.junit.Test;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.util.GeTuiPushUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/5/16.
 */
public class GeTuiPushUtilTest {

    @Test
    public void testPush() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("title", "来客人啦");
        jsonMap.put("content", "大厅1号桌，正在点餐...");
        GeTuiPushUtil.pushMsg(jsonMap);
    }

    @Test
    public void testJson() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("title", "来客人啦");
        jsonMap.put("content", "大厅1号桌，正在点餐...");
        System.out.println(JsonUtil.toJson(jsonMap));
    }
}
