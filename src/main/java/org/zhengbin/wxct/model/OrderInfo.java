package org.zhengbin.wxct.model;

/**
 * 订单详情
 * Created by zhengbinMac on 2017/4/19.
 */
public class OrderInfo {
    private Integer id;             // 订单详情 id
    private Integer order_id;       // 订单 id
    private Integer food_id;        // 菜品id
    private Double price;           // 菜品单价
    private Integer num;            // 数量
    private Double total_price;     // 总价
    private String food_name;       // 菜品名
    private String remark;          // 菜品备注
    private Food food;              // 菜品详情

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getFood_id() {
        return food_id;
    }

    public void setFood_id(Integer food_id) {
        this.food_id = food_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", food_id=" + food_id +
                ", price=" + price +
                ", num=" + num +
                ", total_price=" + total_price +
                ", food_name='" + food_name + '\'' +
                ", remark='" + remark + '\'' +
                ", food=" + food +
                '}';
    }
}
