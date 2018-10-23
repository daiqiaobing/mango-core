package cn.com.bonc.core.conf;

import cn.com.bonc.core.entity.ConfEntity;
import cn.com.bonc.core.entity.ConfResultEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * 文件读取的接口
 * @create 2018-10-22 11:14
 **/

public interface FactoryConfiguration {

    /**
     * 读取对应配置文件的数据
     * @param confEntities 所有文件对应的信息
     * @return 读取文件的数据集合
     * @throws IOException 文件读取操作中的IO异常
     */
    Map<ConfEntity, Map<Object, ConfResultEntity>> read(List<ConfEntity> confEntities) throws IOException;

}
