package cn.mangowork.core.thread.future;

import java.util.Map;

/**
 * @author dailiming 2018-10-26 16:34
 * @version v1
 * 具体实现结果的获取
 **/

public class FutureData<K, V> implements IData<K, V> {

    private HandleData<K, V> realData;
    private boolean isReady = false;

    public HandleData getRealData() {
        return realData;
    }

    public synchronized  void setRealData(HandleData<K, V> realData) {
        if (isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized  Map<K, V> getResult() throws InterruptedException {
        if (!isReady){
            wait();
        }
        return realData.getResult();
    }

}
