package cn.mangowork.core.reflect;


import cn.mangowork.core.exception.NoSuchAnnotation;

import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存注解
 *
 * @author
 * @create 2018-06-14 17:23
 **/

public class BaseCacheAnnotation extends BaseCache{

    /**缓存所有的类对象*/
    private SoftReference<ConcurrentHashMap<String, Annotation[]>> annotations =  new SoftReference<>(new ConcurrentHashMap<>());

    /**缓存所有的类对象*/
    private SoftReference<ConcurrentHashMap<String, Annotation>> allAnnotations =  new SoftReference<>(new ConcurrentHashMap<>());


    /**
     * 获取满足的注解
     * @param t 对象
     * @param annotation 注解
     * @param <T> 范型
     * @param <E> 范型
     * @return 注解
     * @throws NoSuchAnnotation 没有注解异常
     */
    protected static <T, E> Annotation compareAnnotation(T t, E annotation){
        Annotation[] annotations = getAnnotationsByType(t);
        for (Annotation curAnnotation: annotations){
            if (curAnnotation.annotationType().getName().equals(getAnnotationName(annotation))){
                return curAnnotation;
            }
        }
        return null;
    }

    /**
     * 获取某一个注解对应的key
     * @param t 对象t
     * @param e 对象e
     * @param <T> 范型
     * @return 名称
     */
    protected static  <T, E> String getCacheName(T t, E e){
        String cacheName = getBaseCacheName(t);
        return cacheName + "."  + getAnnotationName(e);
    }

    /**
     * 获取注解的名称
     * @param e 对象e
     * @param <E> 范型
     * @return 注解名称
     */
    protected static  <E> String getAnnotationName(E e){
        if (e instanceof Field){
           return ((Field) e).getName();
        }else if(e instanceof Method){
            return ((Method) e).getName();
        }else if (e instanceof Class){
            return ((Class) e).getName();
        }
        return "";
    }


    /**
     *根据类型返回
     * @param t 对象t
     * @param <T> 范型
     * @return 返回所有的注解
     */
    protected static <T> Annotation[] getAnnotationsByType(T t) {
        if (t instanceof Field){
            return ((Field) t).getAnnotations();
        }else if(t instanceof Method){
            return ((Method) t).getAnnotations();
        }else if (t instanceof Class){
            return ((Class) t).getAnnotations();
        }
        return new Annotation[]{};
    }



    /**
     * 当内存对对象回收了之后，再次使用的时候需要初始化
     */
    protected ConcurrentHashMap<String, Annotation[]> getAnnotations(){
        ConcurrentHashMap<String, Annotation[]> classes = this.annotations.get();
        if (classes == null){
            this.annotations = new SoftReference<>(new ConcurrentHashMap<>(16));
        }
        return this.annotations.get();
    }


    /**
     * 当内存对对象回收了之后，再次使用的时候需要初始化
     */
    protected ConcurrentHashMap<String, Annotation> getAllAnnotations(){
        ConcurrentHashMap<String, Annotation> classes = this.allAnnotations.get();
        if (classes == null){
            this.annotations = new SoftReference<>(new ConcurrentHashMap<>(16));
        }
        return this.allAnnotations.get();
    }


}
