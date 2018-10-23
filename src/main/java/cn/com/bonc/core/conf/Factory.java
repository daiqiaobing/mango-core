package cn.com.bonc.core.conf;

import cn.com.bonc.core.constant.EnvConstant;
import cn.com.bonc.core.entity.ConfEntity;
import cn.com.bonc.core.entity.ConfResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dailiming
 * @version v1
 * 获取property对应的配置文件
 * @create 2018-10-22 11:23
 **/

public class Factory implements ResultConfiguration{

    private Logger logger = LoggerFactory.getLogger(Factory.class);

    /**获取全部的数据*/
    private Map<Object, ConfResultEntity> globalResult = new HashMap<>(16);

    /**获取局部数据*/
    private Map<Object, Map<Object, ConfResultEntity>> singleResult = new HashMap<>(16);

    @Override
    public Map<Object, ConfResultEntity> getGlobalResult() {
        return globalResult;
    }

    @Override
    public Map<Object, Map<Object, ConfResultEntity>> getSingleResult() {
        return singleResult;
    }

    @Override
    public void convertResult(List<ConfEntity> confEntityList, String fileType) throws IOException {
        Map<ConfEntity, Map<Object, ConfResultEntity>> results = getResultByType(fileType, confEntityList);
        Set<ConfEntity> confEntities = results.keySet();
        for (ConfEntity confEntity: confEntities){
            switch (confEntity.getScope()){
                case EnvConstant.FILE_SCOPE_GLOBAL:
                    this.globalResult.putAll(results.get(confEntity));
                    break;
                case EnvConstant.FILE_SCOPE_SINGLE:
                    addSingleResult(confEntity, results);
                    break;
                default:
                    logger.debug("配置项value为：{}没有对应配置对应的作用域", confEntity.getValue());
            }
        }
    }

    /**
     * 向全局数据结果集中添加数据
     * @param confEntity 配置项信息
     * @param results 所有的数据结果集合
     */
    private void addSingleResult(ConfEntity confEntity,  Map<ConfEntity, Map<Object, ConfResultEntity>> results) {
        Map<Object, ConfResultEntity> result = new HashMap<>(16);
        // 向局部数据添加数据，先判断key是否存在，如果存在，value追加对应的值
        if (this.singleResult.containsKey(confEntity.getKey())){
            result = this.singleResult.get(confEntity.getKey());
        }
        result.putAll(results.get(confEntity));
        this.singleResult.put(confEntity.getKey(), result);
    }

    /**
     * 根据文件类型获取对应的值
     * @param fileType 文件类型
     * @return 文件中对应的数据结果
     */
    private Map<ConfEntity, Map<Object, ConfResultEntity>> getResultByType(String fileType, List<ConfEntity> entities) throws IOException {
        Map<ConfEntity, Map<Object, ConfResultEntity>> results;
        switch (fileType){
            case EnvConstant.FILE_TYPE_PROPERTY:
                results = new FactoryProperty().read(entities);
                break;
            case EnvConstant.FILE_TYPE_XML:
                results = new FactoryXml().read(entities);
                break;
            case EnvConstant.FILE_TYPE_YAML:
                results = new FactoryYml().read(entities);
                break;
            default:
                logger.debug("文件类型为{}的找不到对应的文件读取的结果", fileType);
                results = new HashMap<>(16);
        }
        return results;
    }
}
