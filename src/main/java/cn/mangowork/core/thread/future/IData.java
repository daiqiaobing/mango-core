package cn.mangowork.core.thread.future;

import java.util.Map;

/**
 * @author dailiming 2018-10-26 16:32
 * @version v1
 * 数据处理接口类
 **/

public interface IData<K, V> {

    /**
     * 获取对应的结果
     * @return 根据范型返回实际类型的数据
     * @throws InterruptedException 异常
     */
    Map<K, V> getResult() throws InterruptedException;

}
