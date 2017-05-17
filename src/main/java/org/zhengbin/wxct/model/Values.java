package org.zhengbin.wxct.model;

/**
 * APICloud 推送 数据结构
 * Created by zhengbinMac on 2017/5/10.
 */
public class Values {
    /**
     title – 消息标题，
     content – 消息内容
     type – 消息类型，1:消息 2:通知
     platform - 0:全部平台，1：ios, 2：android
     groupName - 推送组名，多个组用英文逗号隔开.默认:全部组。eg.group1,group2
     userIds - 推送用户id, 多个用户用英文逗号分隔，eg. user1,user2。
     */
    private String title;
    private String content;
    private int type;
    private int platform;

    @Override
    public String toString() {
        return "Values{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", platform=" + platform +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
