package org.zhengbin.wxct.model;

/**
 * 桌台
 * Created by zhengbinMac on 2017/4/19.
 */
public class Table {
    private Integer id;         // 桌台 id
    private Integer table_id;   // 桌台号
    private String name;        // 桌台名
    private Integer num;        // 人数
    private Integer status;     // 桌台状态
    private Integer group_id;   // 桌台分类id
    private String tableGroupName;  // 桌台分类名
    private TableGroup tableGroup; // 桌台分类

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getTableGroupName() {
        return tableGroupName;
    }

    public void setTableGroupName(String tableGroupName) {
        this.tableGroupName = tableGroupName;
    }

    public TableGroup getTableGroup() {
        return tableGroup;
    }

    public void setTableGroup(TableGroup tableGroup) {
        this.tableGroup = tableGroup;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", table_id=" + table_id +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", status=" + status +
                ", group_id=" + group_id +
                ", tableGroupName='" + tableGroupName + '\'' +
                ", tableGroup=" + tableGroup +
                '}';
    }
}
