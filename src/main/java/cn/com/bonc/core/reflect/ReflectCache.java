package cn.com.bonc.core.reflect;

import cn.com.bonc.core.exception.NoSuchAnnotation;

import javax.swing.event.AncestorEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 缓存类的信息
 *
 * @author
 * @create 2018-06-14 20:04
 **/

public class ReflectCache{

    /**
     * 从缓存中获取所有的方法
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Method[] getDeclaredMethods(Class<T> t){
        return CacheMethod.getDeclaredMethods(t);
    }

    /**
     * 从缓存中获取所有的方法
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Method getDeclaredMethod(Class<T> t, String methodName, Class<?>... parameters) throws NoSuchMethodException {
        return CacheMethod.getDeclaredMethod(t, methodName, parameters);
    }

    /**
     * 如果class不存在，则创建
     * @param clazzName
     * @return
     */
    public static Class getClazz(String clazzName) throws ClassNotFoundException {
        return CacheClass.getClazz(clazzName);
    }

    /**
     * 如果class不存在，则创建
     * @param t
     * @return
     */
    public static <T> Class getClazz(Class<T> t) {
        return CacheClass.getClazz(t);
    }

    /**
     * 如果对象的实例, 获取的时候共用一个对象
     * @param clazzName
     * @return
     */
    public static Object getSingleInstance(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return CacheClass.getSingleInstance(clazzName);
    }

    /**
     * 如果对象的实例, 获取的时候共用一个对象
     * @param t
     * @return
     */
    public static <T> T getSingleInstance(Class<T> t) throws IllegalAccessException, InstantiationException {
        return (T)CacheClass.getSingleInstance(t);
    }


    /**
     * 从缓存中获取所有的方法
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Field[] getDeclaredFields(Class<T> t){
        return CacheField.getDeclaredFields(t);
    }

    /**
     * 获取包含注解的所有的字段
     * @param clazz
     * @param annotation
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> Field[] getFieldByAnnotation(Class<T> clazz, Class<E> annotation) throws InstantiationException, IllegalAccessException {
        return CacheField.getDeclaredFields(clazz, annotation);
    }
    /**
     * 从缓存中获取所有的方法
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Field getDeclaredField(Class<T> t, String fieldName) throws NoSuchFieldException  {
        return CacheField.getDeclaredField(t, fieldName);
    }


    /**
     * 获取对应的缓存的注解
     * @param t
     * @param e
     * @param <T>
     * @return
     * @throws NoSuchAnnotation
     */
    public static <T, E> Annotation getAnnotation(T t, E e) throws InstantiationException, IllegalAccessException {
        return CacheAnnotation.getAnnotation(t, e);
    }

}
