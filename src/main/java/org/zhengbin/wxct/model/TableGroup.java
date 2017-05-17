package org.zhengbin.wxct.model;

import java.util.List;

/**
 * 桌台分类
 * Created by zhengbinMac on 2017/5/12.
 */
public class TableGroup {
    private Integer id;     // 桌台分类id
    private String name;    // 桌台分类名（大厅、包间）
    private List<Table> tableList;  // 某桌台分类下的所有桌台信息

    @Override
    public String toString() {
        return "TableGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tableList=" + tableList +
                '}';
    }

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

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
}
