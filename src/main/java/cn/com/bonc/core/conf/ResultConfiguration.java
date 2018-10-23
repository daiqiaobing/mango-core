package cn.com.bonc.core.conf;

import cn.com.bonc.core.entity.ConfEntity;
import cn.com.bonc.core.entity.ConfResultEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * 读取core.xml中配置的文件
 * @create 2018-10-18 13:39
 **/

public interface ResultConfiguration {

    /**
     * 将所有作为全局的文件中的数据读取到对应的配置文件中
     * @return 全局数据集合
     */
    Map<Object, ConfResultEntity> getGlobalResult();


    /**
     * 将对作用域为局部的结果读取到对应的map中，key为core.xml中对应的key
     * @return 局部数据集合
     */
    Map<Object, Map<Object, ConfResultEntity>> getSingleResult();

    /**
     * 将结果转换为对应的全局数据和局部数据
     * @param confEntityList 所有的配置文件信息
     * @param fileType 文件类型
     * @exception IOException IO异常
     */
    void convertResult(List<ConfEntity> confEntityList, String fileType) throws IOException;
}
