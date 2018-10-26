package cn.mangowork.core.conf;

import cn.mangowork.core.entity.ConfEntity;
import cn.mangowork.core.entity.ConfResultEntity;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming 2018-10-22 11:14
 * @version v1
 * 文件读取的接口
 **/

public interface FactoryConfiguration {

    /**
     * 读取对应配置文件的数据
     * @param confEntities 所有文件对应的信息
     * @return 读取文件的数据集合
     * @throws IOException 文件读取操作中的IO异常
     */
    Map<ConfEntity, Map<Object, ConfResultEntity>> read(List<ConfEntity> confEntities) throws IOException, DocumentException;

}
