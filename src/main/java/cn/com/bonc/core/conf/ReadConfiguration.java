package cn.com.bonc.core.conf;

import cn.com.bonc.core.entity.ConfEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * 读取core.xml中配置的文件
 * @create 2018-10-18 13:39
 **/

public interface ReadConfiguration {

    /**
     * 将所有作为全局的文件中的数据读取到对应的配置文件中
     * @return
     */
    Map<String, String> getGlobalResult();


    /**
     * 将对作用域为局部的结果读取到对应的map中，key为core.xml中对应的key
     * @return
     */
    Map<String, Map> getSingleResult();


    /**
     * 读取对应配置文件的数据
     * @param confEntities  所有文件对应的信息
     */
    void read(List<ConfEntity> confEntities) throws IOException;

}
