package cn.mangowork.core.thread.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dailiming 2018-10-26 16:51
 * @version v1
 * future模式
 **/

public class Future<K, V> {

    private HandleData<K, V> handleData;

    public Future(HandleData<K, V> handle) {
        this.handleData = handle;
    }

    /**
     * 利用线程执行，代理FutureData
     * @return
     */
    public FutureData handle(){
        final FutureData futureData = new FutureData<>();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
        newFixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //RealData的构建很慢，所以放在单独的线程中运行
                handleData.handle();
                futureData.setRealData(handleData);
            }
        });
        return futureData;
    }

}
