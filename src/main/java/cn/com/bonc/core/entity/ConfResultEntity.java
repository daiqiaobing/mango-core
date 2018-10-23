package cn.com.bonc.core.entity;

import java.util.List;

/**
 * @author dailiming
 * @version v1
 * 存放key对应的结果
 * @create 2018-10-19 13:03
 **/

public class ConfResultEntity {

    /**配置文件对应的结果*/
    private Object value;

    /**配置文件对应的结果集*/
    private List<Object> values;


    public ConfResultEntity() {
    }

    public ConfResultEntity(Object value, List<Object> values) {
        this.value = value;
        this.values = values;
    }

    public ConfResultEntity(List<Object> values) {
        this.values = values;
    }

    public ConfResultEntity(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
