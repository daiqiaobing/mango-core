package cn.com.bonc.core.reflect;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存类
 *
 * @author
 * @create 2018-06-14 17:23
 **/

public class CacheClass extends BaseCacheClass{

    private static CacheClass cacheClass = null;

    /**
     * 如果class不存在，则创建
     * @param clazzName
     * @return
     */
    protected static Class getClazz(String clazzName) throws ClassNotFoundException {
        ConcurrentHashMap<String, Class> classes = getInstance().getClasses();
        if (classes == null){
            return getInstance().createClass(clazzName);
        }
        Class aClass = classes.get(clazzName);
        if (aClass == null){
            classes.put(clazzName, getInstance().createClass(clazzName));
        }
        return classes.get(clazzName);
    }

    /**
     * 如果class不存在，则创建
     * @param t
     * @return
     */
    protected static <T> Class getClazz(Class<T> t) {
        ConcurrentHashMap<String, Class> classes = getInstance().getClasses();
        if (classes == null){
            return t.getClass();
        }
        Class aClass = classes.get(t.getName());
        if (aClass == null){
            classes.put(t.getName(), t);
        }
        return classes.get(t.getName());
    }


    /**
     * 如果对象的实例
     * @param clazzName
     * @return
     */
    protected static Object getSingleInstance(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ConcurrentHashMap<String, Object> instances = getInstance().initInstance();
        if (instances == null){
            return  getInstance().createClass(clazzName).newInstance();
        }
        Object instance = instances.get(clazzName);
        if (instance == null){
            instances.put(clazzName, getInstance().createInstance(clazzName));
        }
        return instances.get(clazzName);
    }

    /**
     * 如果对象的实例
     * @param t
     * @return
     */
    public static <T> Object getSingleInstance(Class<T> t) throws IllegalAccessException, InstantiationException {
        ConcurrentHashMap<String, Object> instances = getInstance().initInstance();
        if (instances == null){
            return  t.newInstance();
        }
        Object instance = instances.get(t.getName());
        if (instance == null){
            instances.put(t.getName(), t.newInstance());
        }
        return instances.get(t.getName());
    }

    /**
     * 多线程安全的单列模式
     * @return
     */
    private static CacheClass getInstance(){
        if (cacheClass == null){
            synchronized (CacheClass.class){
                if (cacheClass == null){
                    cacheClass = new CacheClass();
                }
            }
        }
        return cacheClass;
    }

}
