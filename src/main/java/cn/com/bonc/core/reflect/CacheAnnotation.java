package cn.com.bonc.core.reflect;


import cn.com.bonc.core.annotation.NotAnnotation;
import cn.com.bonc.core.exception.NoSuchAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存注解
 * @author
 * @create 2018-06-14 17:23
 **/

public class CacheAnnotation extends BaseCacheAnnotation{

    private static CacheAnnotation cacheAnnotation = null;

    /**
     * 获取对应的缓存的注解,如果没有，则返回null
     * @param t
     * @param e
     * @param <T>
     * @return
     * @throws NoSuchAnnotation
     */
    protected static <T, E> Annotation getAnnotation(T t, E e) {
        ConcurrentHashMap<String, Annotation> allAnnotations = getInstance().getAllAnnotations();
        String cacheName = getCacheName(t, e);
        Annotation b = allAnnotations.get(cacheName);
        if (b != null && b instanceof NotAnnotation){
            return null;
        }
        if (b != null){
            return b;
        }
        Annotation[] annotations = getAnnotations(t);
        if (annotations == null || annotations.length == 0){
            allAnnotations.put(cacheName, getNotAnnotation());
            return null;
        }
        Annotation cur = compareAnnotation(t, e);
        if (cur == null){
            allAnnotations.put(cacheName, getNotAnnotation());
            return null;
        }
        allAnnotations.put(cacheName, cur);
        return cur;
    }

    protected static Annotation getNotAnnotation(){
        Annotation[] annotations = getAnnotations(NotAnnotationClass.class);
        return annotations[0];
    }

    /**
     * 获取对应的缓存的注解
     * @param t
     * @param t
     * @param <T>
     * @return
     * @throws NoSuchAnnotation
     */
    protected static <T> Annotation[] getAnnotations(T t) {
        ConcurrentHashMap<String, Annotation[]> allAnnotations = getInstance().getAnnotations();
        String cacheName = getBaseCacheName(t);
        if (allAnnotations.containsKey(cacheName)){
            return allAnnotations.get(cacheName);
        }
        Annotation[] annotationsByType = getAnnotationsByType(t);
        allAnnotations.put(cacheName, annotationsByType);
        return annotationsByType;
    }

    private static CacheAnnotation getInstance(){
        if (cacheAnnotation == null){
            synchronized (CacheAnnotation.class){
                if (cacheAnnotation == null){
                    cacheAnnotation = new CacheAnnotation();
                }
            }
        }
        return cacheAnnotation;
    }

}
