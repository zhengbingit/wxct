package org.zhengbin.wxct.dao;

import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.wxct.model.User;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;

import java.util.List;

/**
 * User 用户，数据操作类
 * Created by zhengbinMac on 2017/4/18.
 */
@Repository
public class UserDao {
    private static final String COLUMN_TABLE = "id, name, openid, login_time";
    /**
     * 获取 User 表所有信息
     * @return
     */
    public List<User> getUserList() {
        String sql = "select " + COLUMN_TABLE + " from user";
        return DatabaseHelper.queryEntityList(User.class, sql);
    }

    /**
     * 通过用户 id 获取用户信息
     * @return
     */
    public User getUserById(int id) {
        String sql = "select " + COLUMN_TABLE + " from user where id = ?";
        return DatabaseHelper.queryEntity(User.class, sql, id);
    }

}
