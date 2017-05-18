package org.zhengbin.wxct.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import org.zhengbin.wxct.model.Table;
import org.zhengbin.wxct.model.TableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 桌台分组，数据操作
 * Created by zhengbinMac on 2017/5/12.
 */
@Repository
public class TableGroupDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableGroupDao.class);
    private static final String COLUMN_TABLEGROUP = "id, name";

    /**
     * 按桌台分组，获取对应分组的所有桌台信息
     * @return
     */
    public List<TableGroup> getAllTableGroup() {
        List<TableGroup> resultTableGroupList = new ArrayList<TableGroup>();
        String sql = "select " + COLUMN_TABLEGROUP + " from tablegroup";
        resultTableGroupList = DatabaseHelper.queryEntityList(TableGroup.class, sql);

        for (TableGroup tableGroup : resultTableGroupList) {
            int tableGroupId = tableGroup.getId();
            String tableInfoSql = "select * from `table` where group_id = ? order by table_id";
            List<Table> tempTableList = DatabaseHelper.queryEntityList(Table.class, tableInfoSql, tableGroupId);
            tableGroup.setTableList(tempTableList);
        }

        return resultTableGroupList;
    }

    /**
     * 获取所有桌台分类，不包含对应桌台的详情
     * @return
     */
    public List<TableGroup> getAllTableGroups() {
        String sql = "select " + COLUMN_TABLEGROUP + " from tablegroup";
        return DatabaseHelper.queryEntityList(TableGroup.class, sql);
    }

    /**
     * 根据分类id，修改分类名
     * @param groupId
     * @param groupName
     * @return
     */
    public boolean updateTableGroup(int groupId, String groupName) {
        TableGroup tableGroup = new TableGroup();
        tableGroup.setId(groupId);
        tableGroup.setName(groupName);
        return DatabaseHelper.updateEntity(groupId, tableGroup);
    }

    /**
     * 添加新的桌台分类
     * @param groupName
     * @return
     */
    public int addTableGroup(String groupName) {
        TableGroup tableGroup = new TableGroup();
        tableGroup.setName(groupName);
        int num = DatabaseHelper.insertEntity(tableGroup);
        LOGGER.debug("num = {}", num);
        return num;
    }

    /**
     * 删除多个桌台分类
     * @param ids
     * @return
     */
    public int deleteTableGroup(String ids) {
        return DatabaseHelper.deleteEntitysByIds(TableGroup.class, ids);
    }
}
