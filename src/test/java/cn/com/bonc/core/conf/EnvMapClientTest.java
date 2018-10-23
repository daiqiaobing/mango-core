package cn.com.bonc.core.conf;

import junit.framework.TestCase;

/**
 * @author dailiming
 * @version v1
 * 测试配置文件的读取
 * @create 2018-10-22 16:19
 **/

public class EnvMapClientTest extends TestCase {

    
    public void testGetGlobalStrByKey(){
        String name = "log4j.logger.cn.com.bonc";
        String str = EnvMapClient.getStrValueByKey(name);
        String str1 = EnvMapClient.getStrValueByKey("websites.YAML");
    }
    

}
