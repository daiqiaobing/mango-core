package cn.mangowork.core.thread.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实际数据处理的类
 * @author dailiming 2018-10-26 16:35
 * @version v1
 **/

public abstract class HandleData<K, V> implements IData<K, V>{

    /**数据处理的结果集合*/
    protected Map<K, V> data = new ConcurrentHashMap<>(16);

    @Override
    public Map<K, V> getResult() throws InterruptedException {
        handle();
        return this.data;
    }

    /**
     * 抽象handle方法，需要实现handle来实现用户的实际操作
     */
    abstract void handle();

}
