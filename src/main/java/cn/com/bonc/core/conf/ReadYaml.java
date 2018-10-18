package cn.com.bonc.core.conf;

import cn.com.bonc.core.entity.ConfEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * 读取xml中对应的数据
 * @create 2018-10-18 13:52
 **/

public class ReadYaml implements ReadConfiguration{


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

    }
}
