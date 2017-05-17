package org.zhengbin.wxct.test.dao;

import org.zhengbin.wxct.dao.UserDao;
import org.zhengbin.wxct.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhengbinMac on 2017/4/18.
 */
public class UserDaoTest {
    private static Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    private UserDao userDao;
    public UserDaoTest() {
        this.userDao = new UserDao();
    }

    @Test
    public void testGetUserList() {
        List<User> users = userDao.getUserList();
        LOGGER.debug("ListUser : {}", users);
    }

    @Test
    public void testGetUserById() {
        User user = userDao.getUserById(1);
        LOGGER.debug("User : {}", user);
    }

}
