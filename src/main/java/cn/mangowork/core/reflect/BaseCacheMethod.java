package cn.mangowork.core.reflect;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存类
 *
 * @author
 * @create 2018-06-14 17:23
 **/

public class BaseCacheMethod extends BaseCache{

    /**缓存所有的类对象*/
    private SoftReference<ConcurrentHashMap<String, Method[]>> classes =
            new SoftReference<>(new ConcurrentHashMap<>());


    /**
     * 比较参数类型是否相同
     * @param method 方法
     * @param parameters 参数
     * @return 比较参数类型是否相同
     */
    protected static boolean compareMethodType(Method method, Class<?>... parameters){
        Class<?>[] curParameters = method.getParameterTypes();
        if (curParameters.length != parameters.length){
            return false;
        }
        for (int i = 0; i<parameters.length; i++){
            if (!parameters[i].getName().equals(curParameters[i].getName())){
                return false;
            }
        }
        return true;
    }

    /**
     * 当内存对对象回收了之后，再次使用的时候需要初始化
     */
    protected ConcurrentHashMap<String, Method[]> getMethods(){
        ConcurrentHashMap<String, Method[]> classes = this.classes.get();
        if (classes == null){
            this.classes = new SoftReference<>(new ConcurrentHashMap<>(16));
        }
        return this.classes.get();
    }

    /**
     * 根据传递的参数，获取满足条件的method
     * @param methods 方法
     * @param methodName 方法名
     * @param parameters 参数
     * @return 根据传递的参数，获取满足条件的method
     * @throws NoSuchMethodException 没有方法异常
     */
    protected static Method getSatisfyMethod(Method[] methods, String methodName, Class<?>... parameters) throws NoSuchMethodException {
        for (Method curMethod: methods){
            if (curMethod.getName() == methodName){
                if (parameters.length >= 1 && compareMethodType(curMethod, parameters)){
                    return curMethod;
                }else if (parameters.length < 1){
                    return curMethod;
                }
                continue;
            }
        }
        throw new NoSuchMethodException();
    }
}
