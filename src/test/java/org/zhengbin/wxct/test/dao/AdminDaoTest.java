package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.wxct.dao.AdminDao;

/**
 * Created by zhengbinMac on 2017/5/13.
 */
public class AdminDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoTest.class);
    private static AdminDao adminDao;
    public AdminDaoTest() {
        adminDao = new AdminDao();
    }

    @Test
    public void testAdminLogin() {
        String openid = "1";
        String password = adminDao.getAdminPasswordByOpenId(openid);
        LOGGER.debug("password = {}", password);
    }
}
