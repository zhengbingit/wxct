package org.zhengbin.wxct.model;

/**
 * 菜品
 * Created by zhengbinMac on 2017/4/18.
 */
public class Food {
    private Integer id;             // 菜品 id
    private String name;            // 菜品名称
    private Double price;           // 菜品价格
    private Integer rest_num;       // 菜品余量
    private Integer sell_num;       // 菜品历史已售量
    private String detail;          // 菜品详情（描述）
    private String big_img;         // 菜品小图地址
    private String small_img;       // 菜品大图地址
    private Integer group_id;       // 菜品分类 id
    private String group_name;      // 菜品分类名
    private String nature_name;     // 菜品额外属性
    private String unit;            // 菜品的单位
    private Integer off_stock;      // 是否估清
    private String trade_name;      // 菜品售卖时间段
    private String spell;           // 菜品的拼音首字母

    private FoodGroup foodGroup;    // 菜品分类详情
    private FoodNature foodNature;  // 菜品额外属性详情

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rest_num=" + rest_num +
                ", sell_num=" + sell_num +
                ", detail='" + detail + '\'' +
                ", big_img='" + big_img + '\'' +
                ", small_img='" + small_img + '\'' +
                ", group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
                ", nature_name='" + nature_name + '\'' +
                ", unit='" + unit + '\'' +
                ", off_stock=" + off_stock +
                ", trade_name='" + trade_name + '\'' +
                ", spell='" + spell + '\'' +
                ", foodGroup=" + foodGroup +
                ", foodNature=" + foodNature +
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRest_num() {
        return rest_num;
    }

    public void setRest_num(Integer rest_num) {
        this.rest_num = rest_num;
    }

    public Integer getSell_num() {
        return sell_num;
    }

    public void setSell_num(Integer sell_num) {
        this.sell_num = sell_num;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBig_img() {
        return big_img;
    }

    public void setBig_img(String big_img) {
        this.big_img = big_img;
    }

    public String getSmall_img() {
        return small_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getNature_name() {
        return nature_name;
    }

    public void setNature_name(String nature_name) {
        this.nature_name = nature_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getOff_stock() {
        return off_stock;
    }

    public void setOff_stock(Integer off_stock) {
        this.off_stock = off_stock;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    public FoodNature getFoodNature() {
        return foodNature;
    }

    public void setFoodNature(FoodNature foodNature) {
        this.foodNature = foodNature;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
