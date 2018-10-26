package cn.mangowork.core.thread.mw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author dailiming 2018-10-26 12:08
 * @version v1
 * 任务的提交、子任务的分配、处理结果的查询
 * </p> 利用多线程的Master-worker的设计模式来为用户提供固定的接口模式
 **/

public class Master<K, V>  implements IMaster<K, V>{

    /**存放线程的集合，多少个线程处理任务*/
    private Map<String, Thread> workers = new HashMap<>(16);

    /**任务列表*/
    private Queue tasks = new ConcurrentLinkedQueue();

    /**存放执行的结果*/
    private Map<K, V> results = new ConcurrentHashMap<>();

    private Logger logger = LoggerFactory.getLogger(Master.class);


    public Master(Worker<K, V> worker, int threadNum) {
        worker.setResults(this.results);
        worker.setTasks(this.tasks);
        for (int i = 0; i < threadNum; i++) {
            workers.put("线程ID为" + i, new Thread(worker));
        }
    }

    @Override
    public void submit(Object... tasks) {
        for (Object task : tasks) {
            this.tasks.add(task);
        }
    }

    @Override
    public void execute() {
        for (Map.Entry<String, Thread> map: workers.entrySet()){
            logger.info("启动线程{}", map.getKey());
            Thread.State thread = map.getValue().getState();
            map.getValue().start();
        }
    }

    @Override
    public boolean isComplete() {
        for (Map.Entry<String, Thread> map: workers.entrySet()){
            if (Thread.State.TERMINATED != map.getValue().getState()){
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<K, V> getResult() {
        return this.results;
    }

}
