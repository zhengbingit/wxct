package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.dao.TableGroupDao;
import org.zhengbin.wxct.model.TableGroup;

import java.util.List;

/**
 * Created by zhengbinMac on 2017/5/12.
 */
public class TableGroupDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableGroupDaoTest.class);
    private static TableGroupDao tableGroupDao;
    public TableGroupDaoTest() {
        tableGroupDao = new TableGroupDao();
    }

    @Test
    public void testGetAllTableGroup() {
        List<TableGroup> tableGroups = tableGroupDao.getAllTableGroup();
        LOGGER.debug("tableGroups = {}", tableGroups);
        LOGGER.debug("JSON = {}", JsonUtil.toJson(tableGroups));
    }
}
