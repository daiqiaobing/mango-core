package cn.com.bonc.core.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 基本的缓存信息
 * @author
 * @create 2018-06-14 17:25
 **/

public class BaseCache {

    private Logger logger = LoggerFactory.getLogger(BaseCache.class);

    /**
     * 获取t对应的全部的名称，包括 className + (fieldName|methodName) + type(public|protected....)
     * @param t 对象
     * @param <T> 范型
     * @return 返回的对象
     */
    protected static <T> String getBaseCacheName(T t){
        if (t instanceof Field){
            Field field = (Field) t;
            return  field.getDeclaringClass().getName() + "." + field.getName() + "." + Modifier.toString(field.getModifiers());
        }else if (t instanceof Method){
            Method method = (Method) t;
            return method.getDeclaringClass().getName() + "." + method.getName() + "." + Modifier.toString(method.getModifiers());
        }else if (t instanceof Class){
            return ((Class) t).getName();
        }
        return "";
    }

    /**
     * 获取t对应的全部的名称，包括 className + (fieldName|methodName) + type(public|protected....)
     * @param t 对象
     * @param <T> 范型
     * @return 返回的对象
     */
    protected static <T> String getCacheClassName(T t){
        if (t instanceof Field){
            Field field = (Field) t;
            return  field.getDeclaringClass().getName();
        }else if (t instanceof Method){
            Method method = (Method) t;
            return method.getDeclaringClass().getName();
        }else if (t instanceof Class){
            return ((Class) t).getName();
        }
        return "";
    }

    /**
     * 获取对应的类的名+注解名
     * @param t 对象
     * @param <T> 范型
     * @return 返回的对象
     */
    protected static <T, E> String getCacheClassAnnotationName(T t, E e){
        if (t instanceof Class && e instanceof Class){
            return ((Class) t).getName() + e.toString();
        }
        return "";
    }

}
