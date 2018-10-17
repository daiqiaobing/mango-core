package cn.com.bonc.core.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存字段
 * @author
 * @create 2018-06-14 17:23
 **/

public class CacheField extends BaseCacheField{

    private static CacheField cacheMethod = null;

    /**
     * 从缓存中获取所有的字段，存储的格式为className + _DECLARE
     * @param t
     * @param <T>
     * @return
     */
    protected static <T> Field[] getDeclaredFields(Class<T> t){
        ConcurrentHashMap<String, Field[]> allFields = getInstance().getFields();
        if (allFields == null){
            return t.getDeclaredFields();
        }
        Field[] methods = allFields.get(t.getName() + Constant.DECLARE_SUFFIX);
        if (methods == null){
            methods = t.getDeclaredFields();
            allFields.put(t.getName() + Constant.DECLARE_SUFFIX, methods);
        }
        return methods;
    }

    /**
     * 从缓存中获取所有的字段，存储的格式为className + _DECLARE
     * @param t
     * @param <T>
     * @return
     */
    protected static <T> Field getDeclaredField(Class<T> t, String fieldName) throws NoSuchFieldException  {
        ConcurrentHashMap<String, Field[]> allFields = getInstance().getFields();
        if (allFields == null){
            return t.getDeclaredField(fieldName);
        }
        Field[] fields = allFields.get(t.getName() + Constant.DECLARE_SUFFIX);
        if (fields == null){
            fields = t.getDeclaredFields();
            allFields.put(t.getName() + Constant.DECLARE_SUFFIX, fields);
        }
        for (Field curField: fields){
            if (curField.getName() == fieldName){
                 return curField;
            }
        }
        throw new NoSuchFieldException();
    }

    /**
     * 从缓存中获取所有的字段，存储的格式为className + _DECLARE
     * @param t
     * @param <T>
     * @return
     */
    protected static <T> Field[] getFields(Class<T> t){
        ConcurrentHashMap<String, Field[]> allFields = getInstance().getFields();
        if (allFields == null){
            return t.getFields();
        }
        Field[] methods = allFields.get(t.getName());
        if (methods == null){
            allFields.put(t.getName(), t.getDeclaredFields());
        }
        return allFields.get(t.getName());
    }

    /**
     * 从缓存中获取所有的字段，存储的格式为className + _DECLARE
     * @param t
     * @param <T>
     * @return
     */
    protected static <T> Field getField(Class<T> t, String fieldName) throws NoSuchFieldException  {
        ConcurrentHashMap<String, Field[]> allFields = getInstance().getFields();
        if (allFields == null){
            return t.getField(fieldName);
        }
        Field[] fields = allFields.get(t.getName());
        if (fields == null){
            fields = t.getFields();
            allFields.put(t.getName(), fields);
        }
        for (Field curField: fields){
            if (curField.getName() == fieldName){
                return curField;
            }
        }
        throw new NoSuchFieldException();
    }


    private static CacheField getInstance(){
        if (cacheMethod == null){
            synchronized (CacheField.class){
                if (cacheMethod == null){
                    cacheMethod = new CacheField();
                }
            }
        }
        return cacheMethod;
    }

    /**
     * 根据注解获取对应的字段
     * @param clazz
     * @param annotation
     * @param <T>
     * @param <E>
     * @return
     */
    protected static <T, E> Field[] getDeclaredFields(Class<T> clazz, Class<E> annotation) throws IllegalAccessException, InstantiationException {
        String key = getCacheClassAnnotationName(clazz, annotation);
        ConcurrentHashMap<String, Field[]> allFields = getInstance().getFields();
        if (allFields.containsKey(key)){
            return allFields.get(key);
        }
        Field[] declaredFields = getDeclaredFields(clazz);
        ArrayList<Field> fields = new ArrayList<>();
        for (Field field: declaredFields){
            Annotation curAnnotation = ReflectCache.getAnnotation(field, annotation);
            if (curAnnotation != null){
                fields.add(field);
            }
        }
        Field[] curField = fields.toArray(new Field[]{});
        allFields.put(key, curField);
        return curField;
    }
}
