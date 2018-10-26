package cn.mangowork.core.thread.mw;

import java.util.Map;
import java.util.Queue;

/**
 * @author dailiming 2018-10-26 12:43
 * @version v1
 * worker处理事件
 **/

public abstract class Worker<K, V> extends Thread {

    /**任务队列*/
    private Queue<Object> tasks;

    /**处理的结果集合*/
    protected Map<K, V> results;

    @Override
    public void run() {
        while (true){
            Object task = tasks.poll();
            // 任务处理完之后结束线程
            if (task == null){
                break;
            }
            handle(task);
        }
    }

    /**
     * 实际的任务处理
     * @param task 需要处理的任务
     **/
    protected abstract void handle(Object task);


    protected void setTasks(Queue<Object> tasks) {
        this.tasks = tasks;
    }

    protected void setResults(Map results) {
        this.results = results;
    }
}
