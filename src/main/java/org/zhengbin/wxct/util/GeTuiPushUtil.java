package org.zhengbin.wxct.util;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.zhengbin.snowflake.framework.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/5/16.
 */
public class GeTuiPushUtil {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "vmHWcFHLih5r9wIME9POJ6";
    private static String appKey = "rLK6qZ0IPv6ihqx00tXB8";
    private static String masterSecret = "4RkpeuaYHE6RFBd1jfCCi";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    private static IGtPush push;

    static {
        push = new IGtPush(url, appKey, masterSecret);
    }

    public static boolean pushMsg(Map<String, Object> jsonMap) {
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标 App 列表
        // 是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        String contentJson = JsonUtil.toJson(jsonMap);
        message.setData(transmissionTemplateDemo(contentJson));
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        return ret.getResponse().get("result").equals("ok");
    }

    private static TransmissionTemplate transmissionTemplateDemo(String contentJson) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用;2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(contentJson);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    private static NotificationTemplate notificationTemplateDemo(String appId, String appkey, String title, String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用;2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(content);
        // 设置定时展示时间
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        return template;
    }

    public static void main(String[] args) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("欢迎使用个推!");
        template.setText("这是一条推送消息~");
        template.setUrl("http://getui.com");
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表 // 、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
}
