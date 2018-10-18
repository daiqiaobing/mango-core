package cn.com.bonc.core.conf;

import cn.com.bonc.core.constant.EnvConstant;
import cn.com.bonc.core.entity.ConfEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dailiming
 * @version v1
 * 读取core.xml配置文件
 * @create 2018-10-18 13:39
 **/

public class ReadCoreXml {

    /**存放配置文件core.xml的所有的数据*/
    private List<ConfEntity> confEntities = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(ReadCoreXml.class);


    /**
     * 读取core.xml文件，将文件中的数据写入到集合中
     * @throws DocumentException
     */
    private void read() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read( EnvConstant.FILE_BASIC_PATH + EnvConstant.SETTING_FILE);

        // 获取根节点
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element titleElement = rootElement.element("title");
        String title = null;
        if (titleElement != null){
            Object data = titleElement.getData();
            title = data == null ? "" : data.toString();
        }
        for (Element element: elements){
            // 获取节点的数据
            ConfEntity confEntity = getConfEntity(element, title);
            if (confEntity != null){
                this.confEntities.add(confEntity);
            }
        }
    }


    /**
     * 将property节点下的数据转换为对象
     * @param element
     * @return
     */
    private ConfEntity getConfEntity(Element element, String title){
        Iterator iterator = element.nodeIterator();
        ConfEntity confEntity = null;
        while (iterator.hasNext()){
            Object next = iterator.next();
            if (next instanceof Element){
                if (confEntity == null){
                    confEntity = new ConfEntity();
                }
                String value = ((Element) next).getData() == null ? "" : ((Element) next).getData().toString();
                switch (((Element) next).getName()){
                    case "key":
                        confEntity.setKey(value);
                        break;
                    case "value":
                        confEntity.setValue(value);
                        break;
                    case "scope":
                        confEntity.setScope(value);
                        break;
                    case "description":
                        confEntity.setDescription(value);
                        break;
                    case "type":
                        confEntity.setType(value);
                        break;
                }

            }
        }
        if (confEntity != null){
            confEntity.setTitle(title);
        }
        return confEntity;
    }


    /**
     * 获取配置文件中的数据
     * @return
     */
    public List<ConfEntity> getConfEntities() {
        try {
            read();
        } catch (DocumentException e) {
            logger.error("读取配置文件{}失败", EnvConstant.SETTING_FILE);
            new Throwable(e.getMessage());
        }
        return this.confEntities;
    }

}
