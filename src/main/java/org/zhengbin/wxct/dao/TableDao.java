package org.zhengbin.wxct.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import org.zhengbin.wxct.model.Table;
import org.zhengbin.wxct.model.TableGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * table 桌台，数据操作
 * Created by zhengbinMac on 2017/5/5.
 */
@Repository
public class TableDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableDao.class);
    private static final String COLUMN_TABLE = "id, table_id, name, num, status";

    /**
     * 获得桌台的name字段和桌台的分类名
     * tableName : 桌台名
     * tableGroupName : 桌台分类名
     * @param tableId table 表主键 id
     * @return
     */
    public Table getTableInfo(int tableId) {
        String sql = "select  ta.name as tableName, tab.name as tableGroupName from `table` as ta, tablegroup as tab WHERE  ta.id = ? and ta.group_id = tab.id";
        Map<String, Object> map = DatabaseHelper.executeQueryPlus(sql,tableId);
        Table resultTable = new Table();
        resultTable.setName(String.valueOf(map.get("tableName")));
        resultTable.setTableGroupName(String.valueOf(map.get("tableGroupName")));
        return resultTable;
    }

    /**
     * 根据桌台 id，修改桌台信息
     */
    public boolean updateTableStatusById(long id, int status) {
        String sql = "update `table` set status = ? where id = ?";
        return DatabaseHelper.executeUpdate(sql, status, id) == 1;
    }

    /**
     * 获取所有 table 详情，包含分类信息
     * @return
     */
    public List<Table> getAllTable() {
        List<Table> resultList = new ArrayList<Table>();
        String sql = "select" +
                " tabg.id as tabg_id," +
                " tabg.name as tabg_name," +
                " tab.id as tab_id," +
                " tab.table_id as tab_tableid," +
                " tab.name as tab_name," +
                " tab.num as tab_num," +
                " tab.status as tab_status " +
                " from tablegroup as tabg ,`table` as tab " +
                " where tab.group_id = tabg.id;";
        List<Map<String, Object>> tableList = DatabaseHelper.executeQuery(sql);
        LOGGER.debug("tableList = {}", tableList);
        for (Map<String, Object> map : tableList) {
            Table tempTable = new Table();
            tempTable.setId(Integer.parseInt(String.valueOf(map.get("tab_id"))));
            tempTable.setName(String.valueOf(map.get("tab_name")));
            tempTable.setStatus(Integer.parseInt(String.valueOf(map.get("tab_status"))));
            tempTable.setNum(Integer.parseInt(String.valueOf(map.get("tab_num"))));
            tempTable.setTable_id(Integer.parseInt(String.valueOf(map.get("tab_tableid"))));

            TableGroup tempTableGroup = new TableGroup();
            tempTableGroup.setId(Integer.parseInt(String.valueOf(map.get("tabg_id"))));
            tempTableGroup.setName(String.valueOf(map.get("tabg_name")));
            tempTable.setTableGroup(tempTableGroup);

            resultList.add(tempTable);
        }
        return resultList;
    }

    /**
     * 添加桌台信息
     * @return
     */
    public boolean addTable(Table table) {
        String sql = "insert into `table`(group_id, num, name, table_id, status) VALUES (?, ?, ?, ?, ?)";
        return DatabaseHelper.executeUpdate(sql, table.getGroup_id(), table.getNum(), table.getName(), table.getTable_id(), table.getStatus()) == 1;
    }

    /**
     * 修改桌台详情
     * @param table
     * @return
     */
    public boolean updateTableInfo(Table table) {
        String sql = "update `table` set group_id=?, num=? where id=?";
        return DatabaseHelper.executeUpdate(sql, table.getGroup_id(), table.getNum(), table.getId()) == 1;
    }

    /**
     * 删除桌台详情
     * @param id
     * @return
     */
    public boolean deleteTableInfo(int id) {
        String sql = "delete from `table` where id=?";
        return DatabaseHelper.executeUpdate(sql, id) == 1;
    }
}
