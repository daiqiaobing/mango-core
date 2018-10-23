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
     * @param t class
     * @param <T> 范型
     * @return 所有的方法
     */
    public static <T> Method[] getDeclaredMethods(Class<T> t){
        return CacheMethod.getDeclaredMethods(t);
    }

    /**
     * 从缓存中获取所有的方法
     * @param t class
     * @param <T> 范型
     * @return 所有的方法
     */
    public static <T> Method getDeclaredMethod(Class<T> t, String methodName, Class<?>... parameters) throws NoSuchMethodException {
        return CacheMethod.getDeclaredMethod(t, methodName, parameters);
    }

    /**
     * 如果class不存在，则创建
     * @param clazzName class名
     * @return
     */
    public static Class getClazz(String clazzName) throws ClassNotFoundException {
        return CacheClass.getClazz(clazzName);
    }

    /**
     * 如果class不存在，则创建
     * @param t class t
     * @return class类
     */
    public static <T> Class getClazz(Class<T> t) {
        return CacheClass.getClazz(t);
    }

    /**
     * 如果对象的实例, 获取的时候共用一个对象
     * @param clazzName lass名
     * @return 如果对象的实例, 获取的时候共用一个对象
     */
    public static Object getSingleInstance(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return CacheClass.getSingleInstance(clazzName);
    }

    /**
     * 如果对象的实例, 获取的时候共用一个对象
     * @param t class t
     * @return 对象
     */
    public static <T> T getSingleInstance(Class<T> t) throws IllegalAccessException, InstantiationException {
        return (T)CacheClass.getSingleInstance(t);
    }


    /**
     * 从缓存中获取所有的方法
     * @param t class t
     * @param <T> 对象T
     * @return 属性数组
     */
    public static <T> Field[] getDeclaredFields(Class<T> t){
        return CacheField.getDeclaredFields(t);
    }

    /**
     * 获取包含注解的所有的字段
     * @param clazz class类
     * @param annotation 注解
     * @param <T> 范型
     * @param <E> 范型
     * @return 属性
     */
    public static <T, E> Field[] getFieldByAnnotation(Class<T> clazz, Class<E> annotation) throws InstantiationException, IllegalAccessException {
        return CacheField.getDeclaredFields(clazz, annotation);
    }
    /**
     * 从缓存中获取所有的方法
     * @param t class t
     * @param <T> 范型
     * @return 属性
     */
    public static <T> Field getDeclaredField(Class<T> t, String fieldName) throws NoSuchFieldException  {
        return CacheField.getDeclaredField(t, fieldName);
    }


    /**
     * 获取对应的缓存的注解
     * @param t class t
     * @param e class e
     * @param <T> 范型
     * @return 注解
     * @throws NoSuchAnnotation 没有注解异常
     */
    public static <T, E> Annotation getAnnotation(T t, E e) throws InstantiationException, IllegalAccessException {
        return CacheAnnotation.getAnnotation(t, e);
    }

}
