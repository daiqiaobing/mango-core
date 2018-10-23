package cn.com.bonc.core.utils;


import cn.com.bonc.core.annotation.FieldDesc;
import cn.com.bonc.core.annotation.FieldPlaceArray;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 利用反射操作字段信息
 * @author
 * @create 2018-05-07 14:35
 **/

public class FieldsUtil {

    private static Logger logger = LogManager.getLogger(FieldsUtil.class.getName());
    private static ConcurrentHashMap<String, SoftReference<Field[]>> fields = new ConcurrentHashMap<>();
    private static Map<String, SoftReference<Annotation[]>> annotations = new HashMap<>();

    /**
     * 从缓存中获取
     * @param eClass class
     * @param <E> 范型
     * @return 属性
     */
    public static <E> Field[] getFieldByName(Class<E> eClass){
        SoftReference<Field[]> curFields = fields.get(eClass.toString());
        if (curFields == null || curFields.get() == null){
            synchronized (ArrayUtil.class){
                curFields = fields.get(eClass.toString());
                if (curFields == null || curFields.get() == null) {
                    Field[] declaredFields = eClass.getDeclaredFields();
                    for (Field field: declaredFields){
                        //field.setAccessible(true);
                    }
                    fields.put(eClass.toString(), new SoftReference<>(declaredFields));
                    curFields = fields.get(eClass.toString());
                }
            }
        }
        return  curFields.get();
    }

    /**
     * 从缓存中获取
     * @param field 属性
     * @return 注解
     */
    public static Annotation[] getAnnotationByField(Field field){
        SoftReference<Annotation[]> curAnnotation = annotations.get(field.toString());
        if (curAnnotation == null || curAnnotation.get() == null){
            synchronized (FieldsUtil.class){
                curAnnotation = annotations.get(field.toString());
                if (curAnnotation == null || curAnnotation.get() == null) {
                    Annotation[] curAnnotations = field.getDeclaredAnnotations();
                    annotations.put(field.toString(), new SoftReference<>(curAnnotations));
                    curAnnotation = annotations.get(field.toString());
                }
            }
        }
        return  curAnnotation.get();
    }

    /**
     * 获取字段的描述
     * @param field 属性
     * @return  描述
     */
    public static String getDesc(Field field) {
        String result = null;
        try {
            field.setAccessible(true);
            Annotation[] annotation = field.getAnnotations();
            for (Annotation tag : annotation) {
                if (tag instanceof FieldDesc) {
                    if (!((FieldDesc)tag).use()) {
                        break;
                    }
                    result = ((FieldDesc) tag).desc();
                    break;
                }
            }
        } catch (SecurityException e) {
            logger.error(" get the field's description is wrong, the detail is " + e.getMessage());
        }
        return result;
    }

    /**
     * 获取可用的字段 其中FieldDesc注解中use为true
     * @return 属性集合
     */
    public static List<Field> getUseField(Field[] fields){
        LinkedList<Field> curFields = new LinkedList<>();
        for (Field field: fields){
            field.setAccessible(true);
            // 获取所有的注解
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations){
                if (annotation instanceof FieldDesc){
                    if (((FieldDesc) annotation).use()){
                        curFields.add(field);
                    }
                }
            }
        }
        return curFields;
    }

    /**
     * 获取最大的位置
     * @param fields 属性
     * @return 最大
     */
    public static int getMaxPlace(Field[] fields){
        int max = 0;
        for (Field field: fields){
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations){
                if (annotation instanceof FieldPlaceArray){
                    if (((FieldPlaceArray) annotation).use()){
                        int place = ((FieldPlaceArray) annotation).place();
                        max = max > place ? max : place;
                        continue;
                    }
                }
            }
            field.setAccessible(false);
        }
        return max;
    }

    public static <T> List<Field> getPlaceUseField(Field[] fields){
        LinkedList<Field> curFields = new LinkedList<>();
        for (Field field: fields){
            field.setAccessible(true);
            // 获取所有的注解
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations){
                if (annotation instanceof FieldPlaceArray){
                    if (((FieldPlaceArray) annotation).use()){
                        curFields.add(field);
                        continue;
                    }
                }
            }
            annotations = null;
        }
        return curFields;
    }

}
