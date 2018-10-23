package cn.com.bonc.core.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 读取配置文件的工具
 *
 * @author
 * @create 2018-05-10 11:14
 **/

public class PropertiesConfig {

    private static Logger logger = LoggerFactory.getLogger(PropertiesConfig.class.getName());

    private static PropertiesConfig propertiesConfig = null;

    private HashMap<String, String> properties = new HashMap<>();

    private final String SUFFIX = ".properties";

    private PropertiesConfig() {
        loadProperties();
    }

    /**
     * 加载所有的properties文件到Map中
     */
    private void loadProperties(){
        File dir = new File("./");
        List<File> files = getAllFile(dir);
        for (File file: files){
            try {
                logger.info("正在初始化加载文件{}.........", file.getPath());
                Properties property = loadFile(file);
                covertToMap(property);
            } catch (IOException e) {
                logger.error("加载文件的时候发生了异常，异常的信息为：", e.getMessage());
            }
        }
    }

    private List<File> getAllFile(File file){
        LinkedList<File> filesList = new LinkedList<>();
        File[] files = file.listFiles();
        if (files == null){
            filesList.add(file);
            return filesList;
        }
        for (File file1: files){
            if (file1.isFile() && !file1.getName().endsWith(SUFFIX)){
                continue;
            }
            filesList.addAll(getAllFile(file1));
        }
        return filesList;
    }

    /**
     * 将Properties中的数据转换为HashMap
     */
    private void covertToMap(Properties property){
        for (Object key: property.keySet()){
            if (key != null){
                String curKey = (String) key;
                properties.put(curKey, property.getProperty(curKey, ""));
            }
        }
    }

    private Properties loadFile(File file) throws IOException {
        Properties property = new Properties();
        logger.info(file.getAbsolutePath());
        InputStream resourceAsStream = new BufferedInputStream (new FileInputStream(file));
        property.load(resourceAsStream);
        if (resourceAsStream != null){
            resourceAsStream.close();
        }
        return property;
    }

    public static PropertiesConfig getInstance(){
        if (propertiesConfig == null){
            synchronized (PropertiesConfig.class){
                if (propertiesConfig == null){
                    propertiesConfig = new PropertiesConfig();
                    logger.info("init the PropertiesConfig!");
                }
            }
        }
        return propertiesConfig;
    }

    /**
     * 根据key获取配置文件中的value
     * @param key key
     * @return 对应的值
     */
    public String getValue(String ...key){

        if (key.length == 0){
            return "";
        }
        String value = properties.get(key[0]);
        if (value == null || "".equals(value.trim())){
            if (key.length == 2){
                return key[1];
            }
            return "";
        }
        return value;
    }

    /**
     * 根据key获取配置文件中的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static String getValueByKey(String key){
        return getInstance().getValue(key);
    }

    /**
     * 根据key获取配置文件中的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static String getValueByKey(String key, String... val){
        String curVal = getValueByKey(key);
        String def = val.length == 1 ? val[0] : "";
        return "".equals(curVal) ? def : curVal;
    }

    /**
     * 根据key获取配置文件中的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static int getValueByKey(String key, int... num){
        String str = getInstance().getValue(key);
        int length = num.length;
        if ("".equals(str)){
            return length == 1 ? num[0] : Integer.MIN_VALUE;
        }
        try {
            return  Integer.parseInt(str);
        }catch (Exception e){
            return Integer.MIN_VALUE;
        }
    }

    /**
     * 根据key获取配置文件中的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static float getValueByKey(String key, float... num){
        String str = getInstance().getValue(key);
        int length = num.length;
        if ("".equals(str)){
            return length == 1 ? Float.NaN : num[0];
        }
        try {
            return  Float.parseFloat(str);
        }catch (Exception e){
            return Float.NaN;
        }
    }

    /**
     * 根据key获取配置文件中的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static Double getValueByKey(String key, double... num){
        String str = getInstance().getValue(key);
        int length = num.length;
        if ("".equals(str)){
            return length == 1 ? Double.NaN : num[0];
        }
        try {
            return  Double.parseDouble(str);
        }catch (Exception e){
            return Double.NaN;
        }
    }

    /**
     * 根据key获取配置文件中的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static Long getValueByKey(String key, long... num){
        String str = getInstance().getValue(key);
        int length = num.length;
        if ("".equals(str)){
            return length == 1 ? Long.MIN_VALUE : num[0];
        }
        try {
            return  Long.parseLong(str);
        }catch (Exception e){
            return Long.MIN_VALUE;
        }
    }

}
