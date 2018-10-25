package cn.mangowork.core.conf;

import cn.mangowork.core.entity.ConfEntity;
import cn.mangowork.core.entity.ConfResultEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * XML读取工厂
 * @create 2018-10-22 11:16
 **/

public class FactoryXml implements FactoryConfiguration {


    @Override
    public Map<ConfEntity, Map<Object, ConfResultEntity>> read(List<ConfEntity> confEntities) throws IOException {
        return new HashMap<>(16);
    }
}
