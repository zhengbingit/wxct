package org.zhengbin.wxct.model;

import java.util.Map;

/**
 * 调用状态确认
 * Created by zhengbinMac on 2017/5/12.
 */
public class Status {
    private boolean status; // true or false

    private Map<String, Object> values;   // 一个值

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status=" + status +
                ", values=" + values +
                '}';
    }
}
