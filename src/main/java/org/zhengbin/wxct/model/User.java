package org.zhengbin.wxct.model;

/**
 * 用户
 * Created by zhengbinMac on 2017/4/18.
 */
public class User {
    private Integer id;         // 用户 id
    private String name;        // 用户名
    private String openid;      // 微信 openId
    private String login_time;  // 最近登录时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openid='" + openid + '\'' +
                ", login_time='" + login_time + '\'' +
                '}';
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }
}
