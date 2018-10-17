package cn.com.bonc.core.reflect;

import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存字段
 *
 * @author
 * @create 2018-06-14 17:23
 **/

public class BaseCacheField extends BaseCache{

    /**缓存所有的类对象*/
    private SoftReference<ConcurrentHashMap<String, Field[]>> fields =  new SoftReference<>(new ConcurrentHashMap<>());



    /**
     * 当内存对对象回收了之后，再次使用的时候需要初始化
     */
    protected ConcurrentHashMap<String, Field[]> getFields(){
        ConcurrentHashMap<String, Field[]> classes = this.fields.get();
        if (classes == null){
            this.fields = new SoftReference<>(new ConcurrentHashMap<>());
        }
        return this.fields.get();
    }

}
