package cn.com.bonc.core.entity;

/**
 * @author dailiming
 * @version v1
 * core.xml配置文件对应的实体类
 * @create 2018-10-18 13:30
 **/

public class ConfEntity {

    /**core.xml对应的标题*/
    private String title;

    /**配置项对应的key*/
    private String key;

    /**配置项对应的值*/
    private String value;

    /**配置项对应的作用域*/
    private String scope;

    /**配置项对应的类型*/
    private String type;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
