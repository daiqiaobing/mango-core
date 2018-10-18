package cn.com.bonc.core.conf;

import cn.com.bonc.core.constant.EnvConstant;
import cn.com.bonc.core.entity.ConfEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author dailiming
 * @version v1
 * 读取xml中对应的数据
 * @create 2018-10-18 13:52
 **/

public class ReadProperty implements ReadConfiguration{

    private Logger logger = LoggerFactory.getLogger(ReadProperty.class);


    @Override
    public Map<String, String> getGlobalResult() {
        return null;
    }

    @Override
    public Map<String, Map> getSingleResult() {
        return null;
    }

    @Override
    public void read(List<ConfEntity> confEntities) throws IOException {
        for (ConfEntity confEntity: confEntities){
            if (EnvConstant.FILE_TYPE_PROPERTY.equals(confEntity.getType())){
                Properties property = getPropertyByFile(confEntity.getValue());
            }
        }
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
            InputStream resourceAsStream = new BufferedInputStream(new FileInputStream(EnvConstant.FILE_BASIC_PATH + path));
            property.load(resourceAsStream);
            if (resourceAsStream != null){
                resourceAsStream.close();
            }
        }catch (FileNotFoundException e){
            String message = "没有找到对应的配置文件，路径为" + EnvConstant.FILE_BASIC_PATH + path + "\n" + e.getMessage();
            logger.error(message);
            throw new FileNotFoundException(message);
        }catch (IOException e){
            String message = "读取文件失败，路径为" + EnvConstant.FILE_BASIC_PATH + path + "\n" + e.getMessage();
            throw new IOException(message);
        }
        return property;
    }
}
