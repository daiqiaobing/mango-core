package cn.com.bonc.core.reflect;

import cn.com.bonc.core.TestDTO;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试缓存类
 *
 * @author
 * @create 2018-06-14 20:17
 **/

public class TestCacheClass extends TestCase {

    private Logger logger = LoggerFactory.getLogger(TestCacheClass.class);


    public void testCacheInstance() throws InstantiationException, IllegalAccessException {
        TestDTO instance = ReflectCache.getSingleInstance(TestDTO.class);
        instance.name = "三度空间";
        System.out.println(instance.name);
        logger.info(instance.name);
        TestDTO instance1 = ReflectCache.getSingleInstance(TestDTO.class);
        logger.info(instance1.name);
        System.out.println(instance1.name);
    }

}
