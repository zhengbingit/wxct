package org.zhengbin.wxct.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;

/**
 * Created by zhengbinMac on 2017/5/13.
 */
@Repository
public class AdminDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDao.class);

    /**
     * 根据 openid 获取对应的密码
     * @param openid
     * @return
     */
    public String getAdminPasswordByOpenId(String openid) {
        String sql = "select password from admin where openid = ?";
        return DatabaseHelper.queryColumn("password", sql, openid);
    }
}
