package cn.com.bonc.core.reflect;

import cn.com.bonc.core.TestDTO;
import cn.com.bonc.core.annotation.FieldDesc;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试缓存字段
 *
 * @author
 * @create 2018-06-14 20:17
 **/

public class TestCacheField extends TestCase {

    private Logger logger = LoggerFactory.getLogger(TestCacheField.class);


    public void testCacheInstance() {

    }

    public void testField(){
        Field field = ReflectCache.getDeclaredFields(TestDTO.class)[0];
        Annotation[] name = field.getAnnotations();
        logger.info("");
    }
}
