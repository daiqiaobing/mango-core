package cn.com.bonc.core.utils;

import cn.com.bonc.core.annotation.FieldPlaceArray;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 数组的相关工具
 *
 * @author
 * @create 2018-05-15 10:16
 **/

public class ArrayUtil {


    /**
     * 将array转换为对象
     * @param tClass 转为的class
     * @param data 数据
     * @param <T> 范型
     * @return 对象
     * @throws Exception 转换异常
     */
    public static  <T> T arrayToObject(Class<T> tClass, Object[] data) throws Exception {
        T t = tClass.newInstance();
        Field[] fields = t.getClass().getDeclaredFields();
        List<Field> placeUseField = FieldsUtil.getPlaceUseField(fields);
        int max = FieldsUtil.getMaxPlace(fields);
        if ((max + 1) > data.length){
            throw new Exception("the data's length is lower than fields's max place!");
        }
        for (Field field: placeUseField){
            field.setAccessible(true);
            int place = getPlace(field);
            setValueByType(field, t, data[place]);
            field.setAccessible(false);
        }
        return t;
    }

    private static int getPlace(Field field){
        Annotation[] annotations = field.getAnnotations();
        int place = 0;
        for (Annotation annotation: annotations){
            if (annotation instanceof FieldPlaceArray){
                place = ((FieldPlaceArray) annotation).place();
                break;
            }
        }
        return place;
    }

    private static <T> void setValueByType(Field field, T t, Object data) throws IllegalAccessException {
        Type genericType = field.getGenericType();
        if (genericType == String.class){
            field.set(t, String.valueOf(data).trim());
        }else if(genericType == Integer.class || genericType == Integer.TYPE){
            field.setInt(t, Integer.parseInt(String.valueOf(data).trim()));
        }else if(genericType == Boolean.class || genericType == Boolean.TYPE){
            field.setBoolean(t, Boolean.parseBoolean(String.valueOf(data).trim()));
        }else if(genericType == Float.class || genericType == Float.TYPE){

        }else if(genericType == Double.class || genericType == Double.TYPE){
            field.setDouble(t, Double.parseDouble(String.valueOf(data).trim()));
        }
    }


}
