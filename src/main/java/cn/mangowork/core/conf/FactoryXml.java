package cn.mangowork.core.conf;

import cn.mangowork.core.constant.EnvConstant;
import cn.mangowork.core.entity.ConfEntity;
import cn.mangowork.core.entity.ConfResultEntity;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author dailiming 2018-10-22 11:16
 * @version v1
 * XML读取工厂
 **/

public class FactoryXml implements FactoryConfiguration {


    private Logger logger = LoggerFactory.getLogger(FactoryXml.class);

    @Override
    public Map<ConfEntity, Map<Object, ConfResultEntity>> read(List<ConfEntity> confEntities) throws IOException{
        Map<ConfEntity, Map<Object, ConfResultEntity>> result = new HashMap<>(16);
        for (ConfEntity confEntity: confEntities){
            if (EnvConstant.FILE_TYPE_XML.equals(confEntity.getType())){
                Map map = null;
                try {
                    map = getXMLByFile(confEntity.getValue());
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                    throw new FileNotFoundException("没有找到文件" + confEntity.getValue());
                }catch (DocumentException e){
                    e.printStackTrace();
                    logger.error("XML文件解析错误{}", e.getMessage());
                }
                result.put(confEntity, map);
            }
        }
        return result;
    }

    /**
     * 读取文件，并且将文件中的数据转换为Map
     * @param path 文件路径
     * @return 数据集合
     * @throws FileNotFoundException 文件没有找到异常
     * @throws DocumentException 文件解析异常
     */
    private Map<Object, ConfResultEntity> getXMLByFile(String path) throws FileNotFoundException, DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new FileInputStream(new File(EnvConstant.FILE_BASIC_PATH + path)));
        // 获取根节点
        Element rootElement = document.getRootElement();
        return getChildResult(rootElement, "");
    }

    /**
     *能处理的节点为除了叶子节点其他节点不能重读
     *</p>     <property>
     *                  <key>aoi</key>
     *                  <value>test.properties</value>
     *                  <type>property</type>
     *                 <scope>global</scope>
     *          </property>
     * </p>读取的结果中包含了property.key（aoi）、property.value(test.properties) 、property.type(property) 等
     * @param element 根节点
     * @param baseKey 初始化为"",传递基础key
     * @return 返回处理的结果
     */
    private Map<Object,ConfResultEntity> getChildResult(Element element, String baseKey){
        // 存放遍历的结果
        Map<Object,ConfResultEntity> result = new HashMap<>(16);
        Iterator iterator = element.nodeIterator();
        String key = element.getName();
        if (!"".equals((baseKey.trim()))){
            key = baseKey + EnvConstant.FILE_KEY_JOIN + key;
        }else {
            key = element.getName();
        }
        while (iterator.hasNext()){
            Object next = iterator.next();
            // 包含子节点
            if (next instanceof Element){
                result.putAll(getChildResult((Element) next, key));
            }else if (next instanceof Text){
                /*如果存在，则结果为数组，不存在，则创建*/
                String data = ((Text)next).getText();
                if (data == null || "".equals(data.trim())){
                    continue;
                }
                if (result.containsKey(key)){
                    result.get(key).getValues().add(data);
                }else {
                    result.put(key, new ConfResultEntity(data));
                }
            }
        }
        return result;
    }


}
