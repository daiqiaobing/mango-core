package cn.com.bonc.core.reflect;


import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存类
 *
 * @author
 * @create 2018-06-14 17:23
 **/

public class BaseCacheClass extends BaseCache{

    /**缓存所有的类对象*/
    protected SoftReference<ConcurrentHashMap<String, Class>> classes = new SoftReference<>(new ConcurrentHashMap<String, Class>());

    /**缓存所有的类的实例*/
    protected SoftReference<ConcurrentHashMap<String, Object>> instances = new SoftReference<>(new ConcurrentHashMap<String, Object>());


    /**
     * 创建实例
     * @return
     */
    protected Object createInstance(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return this.createClass(clazzName).newInstance();
    }


    /**
     * 创建类对象
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    protected Class createClass(String className) throws ClassNotFoundException {
        return  Class.forName(className);
    }

    /**
     * 当内存对对象回收了之后，再次使用的时候需要初始化
     */
    protected ConcurrentHashMap<String, Class> getClasses(){
        ConcurrentHashMap<String, Class> classes = this.classes.get();
        if (classes == null){
            this.classes = new SoftReference<>(new ConcurrentHashMap<String, Class>());
        }
        return this.classes.get();
    }

    /**
     * 当内存将所有的实例回收了之后需要再次初始化
     */
    protected ConcurrentHashMap<String, Object> initInstance(){
        ConcurrentHashMap<String, Object> instances = this.instances.get();
        if (instances == null){
            this.instances = new SoftReference<>(new ConcurrentHashMap<String, Object>());
        }
        return this.instances.get();
    }

}
