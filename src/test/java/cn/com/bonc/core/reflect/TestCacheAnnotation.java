package cn.com.bonc.core.reflect;

import cn.com.bonc.core.TestDTO;
import cn.com.bonc.core.annotation.FieldDesc;
import cn.com.bonc.core.annotation.FieldPlaceArray;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 测试缓存类
 *
 * @author
 * @create 2018-06-14 20:17
 **/

public class TestCacheAnnotation extends TestCase {

    private Logger logger = LoggerFactory.getLogger(TestCacheAnnotation.class);


    public void testCacheInstance() throws InstantiationException, IllegalAccessException {

    }

    public void testCache() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field name = ReflectCache.getDeclaredField(TestDTO.class, "name");
        System.out.println(name.getClass());

        ReflectCache.getAnnotation(name,   FieldPlaceArray.class);
        ReflectCache.getAnnotation(name,   FieldDesc.class);
    }

}
