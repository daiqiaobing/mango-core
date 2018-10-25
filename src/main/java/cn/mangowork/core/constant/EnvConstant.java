package cn.mangowork.core.constant;

/**
 * @author dailiming
 * @version v1
 * 环境对应的常量
 * @create 2018-10-18 12:01
 **/

public class EnvConstant {

    /**
     * 环境的配置文件的名称，默认的配置文件的路径在class的根目录下，名称为：core.xml
     */
    public static final String SETTING_FILE = "core.xml";

    /**xml文件对应的类型*/
    public static final String FILE_TYPE_XML = "xml";

    /**yaml文件对应的类型*/
    public static final String FILE_TYPE_YAML = "yaml";

    /**property文件对应的类型*/
    public static final String FILE_TYPE_PROPERTY = "property";

    /**项目class对应的基本路径*/
    public static final String FILE_BASIC_PATH = EnvConstant.class.getResource("/").getPath();

    /**文件对应的作用域为：global*/
    public static final String FILE_SCOPE_GLOBAL = "global";

    /**文件对应的作用域为：single*/
    public static final String FILE_SCOPE_SINGLE = "single";

    /**yaml中key连接符号*/
    public static final String FILE_YML_KEY_JOIN = ".";

}
