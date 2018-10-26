package cn.mangowork.core.conf;

import junit.framework.TestCase;

/**
 * @author dailiming 2018-10-22 16:19
 * @version v1
 * 测试配置文件的读取
 **/

public class EnvMapClientTest extends TestCase {

    
    public void testGetGlobalStrByKey(){
        String name = "log4j.logger.cn.com.bonc";
        String str = EnvMapClient.getStringByKey(name);
        String str1 = EnvMapClient.getStringByKey("websites.YAML");
        String title = EnvMapClient.getStringByKey("configuration.title");
        String title1 = EnvMapClient.getSingleStringByKey("key1", "configuration.title");
    }
    

}
