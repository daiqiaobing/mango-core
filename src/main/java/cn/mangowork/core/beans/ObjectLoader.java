package cn.mangowork.core.beans;

import cn.mangowork.core.annotation.FieldPlaceArray;
import cn.mangowork.core.reflect.ReflectCache;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 对象的加载, 利用反射将数组转换为对象
 *
 * @author
 * @create 2018-06-15 16:48
 **/

public class ObjectLoader<T> extends ObjectBaseLoader{

    /**
     * 将数组中的数据转换为实体
     * @param tClass class类
     * @param data 数据
     * @param <T> 范型
     * @return 实体对象
     * @throws Exception 转换过程出现异常
     */
    public static  <T> T getBean(Class<T> tClass, Object[] data) throws Exception {
        T t = tClass.newInstance();
        Field[] fields = ReflectCache.getFieldByAnnotation(tClass, FieldPlaceArray.class);
        String name = tClass.getName();
        Integer max = getCachePlace().get(name);
        if (null == max){
            max = getMaxPlace(fields);
            getCachePlace().put(name, max);
        }
        if ((max + 1) > data.length){
            throw new Exception("the data's length is lower than fields's max place!");
        }
        for (Field field: fields){
            String curName = tClass.getName() + field.getName();
            Integer place = getCachePlace().get(curName);
            if (place == null){
                place = getPlace(field);
            }
            if (place < 0){
                continue;
            }
            setValueByType(field, t, data[place]);
        }
        return t;
    }

    /**
     * 将values赋值到对象中
     * @param clazz class类
     * @param values 值
     * @param <T> 范型
     * @return 实体对象
     */
    public static <T> T getBean(Class<T> clazz, Map<String, String> values) throws IllegalAccessException, InstantiationException {
        // todo
        return null;
    }

}
