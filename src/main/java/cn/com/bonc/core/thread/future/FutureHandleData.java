package cn.com.bonc.core.thread.future;

/**
 * 文件数据读取
 *
 * @author
 * @create 2018-05-15 13:49
 **/

public interface FutureHandleData<T>  {

    BaseData request(T t);

}
