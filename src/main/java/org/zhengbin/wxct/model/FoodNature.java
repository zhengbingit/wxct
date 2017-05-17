package org.zhengbin.wxct.model;

/**
 * 菜品额外属性
 * Created by zhengbinMac on 2017/4/18.
 */
public class FoodNature {
    private Integer id;         // 菜品额外属性 id
    private String name;    // 菜品额外属性名
    private String detail;  // 菜品额外属性内容

    @Override
    public String toString() {
        return "FoodNature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
