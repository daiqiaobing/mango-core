package cn.mangowork.core.conf;

import cn.mangowork.core.constant.EnvConstant;
import cn.mangowork.core.entity.ConfEntity;
import cn.mangowork.core.entity.ConfResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

/**
 * @author dailiming
 * @version v1
 * yml读取工厂
 * @create 2018-10-22 11:16
 **/

public class FactoryYml implements FactoryConfiguration {

    private Logger logger = LoggerFactory.getLogger(FactoryProperty.class);


    @Override
    public Map<ConfEntity, Map<Object, ConfResultEntity>> read(List<ConfEntity> confEntities) throws IOException {
        Map<ConfEntity, Map<Object, ConfResultEntity>> result = new HashMap<>(16);
        for (ConfEntity confEntity: confEntities){
            if (EnvConstant.FILE_TYPE_YAML.equals(confEntity.getType())){
                Map map = getYmlByFile(confEntity.getValue());
                Map<Object, ConfResultEntity> curResult = handleYmlResult(map, "");
                result.put(confEntity, curResult);
            }
        }
        return result;
    }

    /**
     * 通过yaml读取的包含了很多的层级关系，一个map中嵌套这另外一个map，现在将简化，多个map用"."和key连接起来
     * @param map yaml读取数据之后转换的map
     * @param baseKey 基本的key，用这个来进行拼接
     * @return  数据处理之后的集合
     */
    private Map<Object,ConfResultEntity> handleYmlResult(Map map, String baseKey) {
        Map<Object,ConfResultEntity> result = new HashMap<>(16);
        String curKey;
        if (baseKey == null || "".equals(baseKey.trim())){
            curKey = "";
        }else {
            curKey = baseKey.trim() + EnvConstant.FILE_YML_KEY_JOIN;
        }

        for (Object key: map.keySet()){
            Object object = map.get(key);
            if (object instanceof Map){
                result.putAll(handleYmlResult((Map) object, String.valueOf(curKey + key)));
            }else if (object instanceof List){
                result.put(curKey + key, new ConfResultEntity((List<Object>) object));
            }else {
                result.put(curKey+ key,  new ConfResultEntity(object));
            }
        }
        return result;
    }



    /**
     * 通过文件名读取文件，并且将文件转换为Properties对象
     * @param path 文件路径
     * @return Properties
     */
    private Map<Object, Object> getYmlByFile(String path) throws IOException {
        Map results;
        Yaml yaml = new Yaml();
        logger.info("读取配置文件{}", path);
        try {
            InputStream resourceAsStream = new BufferedInputStream(new FileInputStream(EnvConstant.FILE_BASIC_PATH + path));
            results = (Map)yaml.load(resourceAsStream);
            if (resourceAsStream != null){
                resourceAsStream.close();
            }
        }catch (FileNotFoundException e){
            String message = "没有找到对应的配置文件，路径为" + EnvConstant.FILE_BASIC_PATH + path + "\n" + e.getMessage();
            logger.error(message);
            throw new FileNotFoundException(message);
        }
        return results;
    }

}
