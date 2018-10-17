package cn.com.bonc.core.reflect;

import junit.framework.TestCase;

/**
 * 反射缓存方法测试
 *
 * @author
 * @create 2018-06-14 19:43
 **/

public class TestCacheMethod extends TestCase {

    public void testA(String a){

    }

    public void testGetMethod() throws NoSuchMethodException {

        CacheMethod.getDeclaredMethod(CacheClass.class, "getClazz", String.class, Integer.class);
    }

}
