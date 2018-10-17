package cn.com.bonc.core.beans;

import cn.com.bonc.core.annotation.FieldPlaceArray;
import cn.com.bonc.core.reflect.ReflectCache;

import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 对象加载的基本的操作方法，只提供给ObjectLoader使用
 * @create 2018-06-15 16:49
 **/

public class ObjectBaseLoader {

    /**缓存最大位置*/
    protected static SoftReference<Map<String, Integer>> cachePlace = new SoftReference<>(new HashMap<>());

    protected ObjectBaseLoader(){
    }



    /**
     * 获取该字段在文件中的位置
     * @param field
     * @return
     */
    protected static int getPlace(Field field) throws IllegalAccessException, InstantiationException {
        Annotation annotation = ReflectCache.getAnnotation(field, FieldPlaceArray.class);
        if (annotation instanceof FieldPlaceArray){
            if (!((FieldPlaceArray) annotation).use()){
                return -1;
            }
            return ((FieldPlaceArray) annotation).place();
        }
        return -1;
    }

    /**
     * 根据类型赋值
     * @param field
     * @param t
     * @param data
     * @param <T>
     * @throws IllegalAccessException
     */
    protected static <T> void setValueByType(Field field, T t, Object data) throws IllegalAccessException {
        Type genericType = field.getGenericType();
        if (genericType == String.class){
            field.set(t, String.valueOf(data).trim());
        }else if(genericType == Integer.class || genericType == Integer.TYPE){
            field.setInt(t, Integer.parseInt(data + ""));
        }else if(genericType == Boolean.class || genericType == Boolean.TYPE){
            field.setBoolean(t, Boolean.parseBoolean(data + ""));
        }else if(genericType == Float.class || genericType == Float.TYPE){
            field.setFloat(t, Float.parseFloat(data + ""));
        }else if(genericType == Double.class || genericType == Double.TYPE){
            field.setDouble(t, Double.parseDouble(data + ""));
        }
    }


    /**
     * 获取最大的位置
     * @param fields
     * @return
     */
    protected static int getMaxPlace(Field[] fields) throws IllegalAccessException, InstantiationException {
        int max = 0;
        for (Field field: fields) {
            field.setAccessible(true);
            Annotation curAnnotation = ReflectCache.getAnnotation(field, FieldPlaceArray.class);
            if (curAnnotation == null) {
                continue;
            }
            if (curAnnotation instanceof FieldPlaceArray) {
                if (((FieldPlaceArray) curAnnotation).use()) {
                    int place = ((FieldPlaceArray) curAnnotation).place();
                    max = max > place ? max : place;
                }
            }
        }
        return max;
    }

    /**
     * 获取缓存对象
     * @return
     */
    protected static Map<String, Integer> getCachePlace(){
        if (cachePlace.get() == null){
            synchronized (ObjectBaseLoader.class){
                if (cachePlace.get() == null){
                    cachePlace = new SoftReference<>(new HashMap<>());
                }
            }
        }
        return cachePlace.get();
    }

}
