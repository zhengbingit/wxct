package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.constants.TableStatusConstant;
import org.zhengbin.wxct.dao.TableDao;
import org.zhengbin.wxct.model.Table;

import java.util.List;

/**
 * Created by zhengbinMac on 2017/5/5.
 */
public class TableDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableDaoTest.class);
    private static TableDao tableDao;
    public TableDaoTest() {
        tableDao = new TableDao();
    }

    @Test
    public void testGetAllTables() {
        List<Table> tableList = tableDao.getAllTable();
        LOGGER.debug("tables : {}", tableList);
        LOGGER.debug("JSON : {}", JsonUtil.toJson(tableList));
    }

    @Test
    public void testUpdateTableStatusById() {
        long id = 1;
        int status = TableStatusConstant.CHU_CAN_ZHONG;
        LOGGER.debug("updateTableStatus : {}", tableDao.updateTableStatusById(id, status));
    }

    @Test
    public void testGetTableInfo() {
        int tableId = 1;
        LOGGER.debug("tableInfo = {}", tableDao.getTableInfo(tableId));
    }
}
