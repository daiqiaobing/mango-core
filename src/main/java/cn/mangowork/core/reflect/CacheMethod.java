package cn.mangowork.core.reflect;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存方法
 *
 * @author
 * @create 2018-06-14 17:23
 **/

public class CacheMethod extends BaseCacheMethod{

    private static CacheMethod cacheMethod = null;

    /**
     * 从缓存中获取所有的方法, 缓存中的存取的方式为对应的className + _DECLARE
     * @param t class
     * @param <T> 范型
     * @return 所有的方法
     */
    protected static <T> Method[] getDeclaredMethods(Class<T> t){
        ConcurrentHashMap<String, Method[]> allMethods = getInstance().getMethods();
        if (allMethods == null){
            return t.getDeclaredMethods();
        }
        Method[] methods = allMethods.get(t.getName() + Constant.DECLARE_SUFFIX);
        if (methods == null){
            allMethods.put(t.getName() + Constant.DECLARE_SUFFIX, t.getDeclaredMethods());
        }
        return allMethods.get(t.getName());
    }

    /**
     * 从缓存中获取所有的方法,缓存中的存取的方式为对应的className
     * @param t class
     * @param <T> 范型
     * @return 所有的方法
     */
    protected static <T> Method[] getMethods(Class<T> t){
        ConcurrentHashMap<String, Method[]> allMethods = getInstance().getMethods();
        if (allMethods == null){
            return t.getMethods();
        }
        Method[] methods = allMethods.get(t.getName());
        if (methods == null){
            allMethods.put(t.getName(), t.getDeclaredMethods());
        }
        return allMethods.get(t.getName());
    }

    /**
     * 从缓存中获取满足的方法
     * @param t class
     * @param <T> 范型
     * @return 方法
     */
    protected static <T> Method getDeclaredMethod(Class<T> t, String methodName, Class<?>... parameters) throws NoSuchMethodException {
        ConcurrentHashMap<String, Method[]> allMethods = getInstance().getMethods();
        if (allMethods == null){
            return t.getDeclaredMethod(methodName);
        }
        Method[] methods = allMethods.get(t.getName() + Constant.DECLARE_SUFFIX);
        if (methods == null){
            methods = t.getDeclaredMethods();
            allMethods.put(t.getName() + Constant.DECLARE_SUFFIX, methods);
        }
        return getSatisfyMethod(methods, methodName, parameters);
    }

    /**
     * 从缓存中获取满足的方法
     * @param t class
     * @param <T> 范型
     * @return 的方法
     */
    protected static <T> Method getMethod(Class<T> t, String methodName, Class<?>... parameters) throws NoSuchMethodException {
        ConcurrentHashMap<String, Method[]> allMethods = getInstance().getMethods();
        if (allMethods == null){
            return t.getMethod(methodName);
        }
        Method[] methods = allMethods.get(t.getName());
        if (methods == null){
            methods = t.getDeclaredMethods();
            allMethods.put(t.getName(), methods);
        }
        return getSatisfyMethod(methods, methodName, parameters);
    }

    private static CacheMethod getInstance(){
        if (cacheMethod == null){
            synchronized (CacheMethod.class){
                if (cacheMethod == null){
                    cacheMethod = new CacheMethod();
                }
            }
        }
        return cacheMethod;
    }

}
