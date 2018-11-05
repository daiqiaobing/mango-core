package cn.mangowork.core.conf;

import cn.mangowork.core.constant.EnvConstant;
import cn.mangowork.core.entity.ConfEntity;
import cn.mangowork.core.entity.ConfResultEntity;
import cn.mangowork.core.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @author dailiming
 * @version v1
 * property读取工厂
 * @create 2018-10-22 11:16
 **/

public class
FactoryProperty implements FactoryConfiguration {

    private Logger logger = LoggerFactory.getLogger(FactoryProperty.class);

    @Override
    public Map<ConfEntity, Map<Object, ConfResultEntity>> read(List<ConfEntity> confEntities) throws IOException {
        Map<ConfEntity, Map<Object, ConfResultEntity>> result = new HashMap<>(16);
        for (ConfEntity confEntity: confEntities){
            if (EnvConstant.FILE_TYPE_PROPERTY.equals(confEntity.getType())){
                Map<Object, ConfResultEntity> curResult = new HashMap<>(16);
                Properties property = getPropertyByFile(confEntity.getValue());
                Enumeration<Object> keys = property.keys();
                while (keys.hasMoreElements()){
                    Object key = keys.nextElement();
                    if (key == null){
                        continue;
                    }
                    curResult.put(key,  new ConfResultEntity(property.get(key)));
                }
                result.put(confEntity, curResult);
            }
        }
        return result;
    }

    /**
     * 通过文件名读取文件，并且将文件转换为Properties对象
     * @param path 文件路径
     * @return Properties
     */
    private Properties getPropertyByFile(String path) throws IOException {
        Properties property = new Properties();
        logger.info("读取配置文件{}", path);
        try {
            InputStream resourceAsStream = new BufferedInputStream(new FileInputStream(FileUtils.getFileByPath(path)));
            property.load(resourceAsStream);
            if (resourceAsStream != null){
                resourceAsStream.close();
            }
        }catch (FileNotFoundException e){
            String message = "没有找到对应的配置文件" + path + "\n" + e.getMessage();
            logger.error(message);
            throw new FileNotFoundException(message);
        }catch (IOException e){
            String message = "读取文件失败" + path + "\n" + e.getMessage();
            throw new IOException(message);
        }
        return property;
    }

}
