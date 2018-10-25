package cn.mangowork.core.conf;

import cn.mangowork.core.constant.EnvConstant;
import cn.mangowork.core.entity.ConfEntity;
import cn.mangowork.core.entity.ConfResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * 获取配置信息对应的值,获取流程，当需要使用的时候将数据加载到内存中(只加载一次)
 * @create 2018-10-22 11:40
 **/

public class EnvMapClient {

    private static Logger logger = LoggerFactory.getLogger(EnvMapClient.class);


    /**获取全部的数据*/
    private static Map<Object, ConfResultEntity> globalResult = new HashMap<>(16);

    /**获取局部数据*/
    private static Map<Object, Map<Object, ConfResultEntity>> singleResult = new HashMap<>(16);

    private static List<ConfEntity> confEntities = null;


    static {
        // 只加载一次，获取到配置文件中的数据并且赋值
        setResult();
    }

    /**
     * 根据key获取对应的value
     * @param key 索引
     * @return 索引对应的值
     */
    public static ConfResultEntity getByKey(Object key){
        return  globalResult.get(key);
    }


    /**
     * 根据key获取对应的value
     * @param key1  配置文件中的索引1
     * @param key2  索引2
     * @return 索引1以及索引2所对应的值
     */
    public static ConfResultEntity getSingleByKey(Object key1, Object key2){
        Map<Object, ConfResultEntity> result1 = singleResult.get(key1);
        return result1.get(key2);
    }



    /**
     * 获取字符串结果
     * @param key 索引
     * @return 索引对应的值
     */
    public static String getStrValueByKey(Object key){
        ConfResultEntity obj  = getByKey(key);
        String result = null;
        if (obj != null){
            result = String.valueOf(obj.getValue());
        }
        return result;
    }

    /**
     * 获取字符串结果
     * @param key1  配置文件中的索引1
     * @param key2  索引2
     * @return 索引1以及索引2所对应的值
     */
    public static String getSingleStrValueByKey(Object key1, Object key2){
        ConfResultEntity obj  = getSingleByKey(key1, key2);
        String result = null;
        if (obj != null){
            result = String.valueOf(obj.getValue());
        }
        return result;
    }

    /**
     * 获取整数结果
     * @param key 索引
     * @return 索引对应的值
     */
    public static Integer getIntValueByKey(Object key){
        ConfResultEntity obj  = getByKey(key);
        Integer result = null;
        if (obj != null){
            try {
                result = Integer.parseInt(String.valueOf(obj.getValue()));
            }catch (NumberFormatException e){
                throw new NumberFormatException("Object转换为Integer错误，不能强制转换！");
            }
        }
        return result;
    }

    /**
     * 获取字符串结果
     * @param key1  配置文件中的索引1
     * @param key2  索引2
     * @return 索引1以及索引2所对应的值
     */
    public static Integer getSingleIntValueByKey(Object key1, Object key2){
        ConfResultEntity obj  = getSingleByKey(key1, key2);
        Integer result = null;
        if (obj != null){
            try {
                result = Integer.parseInt(String.valueOf(obj.getValue()));
            }catch (NumberFormatException e){
                throw new NumberFormatException("Object转换为Integer错误，不能强制转换！");
            }
        }
        return result;
    }


    /**
     * 获取浮点数结果
     * @param key 索引
     * @return 索引对应的值
     */
    public static Float getFloatValueByKey(Object key){
        ConfResultEntity obj  = getByKey(key);
        Float result = null;
        if (obj != null){
            try {
                result = Float.parseFloat(String.valueOf(obj.getValue()));
            }catch (NumberFormatException e){
                throw new NumberFormatException("Object转换为Float错误，不能强制转换！");
            }
        }
        return result;
    }

    /**
     * 获取浮点数结果
     * @param key1  配置文件中的索引1
     * @param key2  索引2
     * @return 索引1以及索引2所对应的值
     */
    public static Float getSingleFloatValueByKey(Object key1, Object key2){
        ConfResultEntity obj  = getSingleByKey(key1, key2);
        Float result = null;
        if (obj != null){
            try {
                result = Float.parseFloat(String.valueOf(obj.getValue()));
            }catch (NumberFormatException e){
                throw new NumberFormatException("Object转换为Float错误，不能强制转换！");
            }
        }
        return result;
    }


    /**
     * 获集合结果
     * @param key 索引
     * @return 索引对应的值
     */
    public static List getListValueByKey(Object key){
        ConfResultEntity obj  = getByKey(key);
        List result = null;
        if (obj != null){
            result = obj.getValues();
        }
        return result;
    }

    /**
     * 获取集合结果
     * @param key1  配置文件中的索引1
     * @param key2  索引2
     * @return 索引1以及索引2所对应的值
     */
    public static List getSingleListValueByKey(Object key1, Object key2){
        ConfResultEntity obj  = getSingleByKey(key1, key2);
        List result = null;
        if (obj != null){
            result = obj.getValues();
        }
        return result;
    }
    /**
     * 获取所有的文件的配置信息
     * @return
     */
    private static List<ConfEntity> getConfEntities() {
        if (confEntities == null) {
            synchronized (EnvMapClient.class) {
                if (confEntities == null) {
                    confEntities = new ReadCoreXml().getConfEntities();
                }
            }
        }
        return confEntities;
    }


    /**
     * 给对象赋值
     * @throws IOException
     */
    private static void setResult() {
        ResultConfiguration factory = new Factory();
        // 读取配置文件
        try {
            factory.convertResult(getConfEntities(), EnvConstant.FILE_TYPE_PROPERTY);
            factory.convertResult(getConfEntities(), EnvConstant.FILE_TYPE_XML);
            factory.convertResult(getConfEntities(), EnvConstant.FILE_TYPE_YAML);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取配置文件失败", e.getMessage());
        }
        // 获取结果
        singleResult = factory.getSingleResult();
        globalResult = factory.getGlobalResult();
    }

}
