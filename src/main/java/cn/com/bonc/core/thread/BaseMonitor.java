package cn.com.bonc.core.thread;


/**
 * 任务分配
 *
 * @author
 * @create 2018-05-08 12:47
 **/

public interface BaseMonitor<T, R> {

    /**
     * 提交任务
     * @param tasks 任务
     */
    void submit(T ...tasks);

    /**
     * 执行任务
     */
    void execute();

    /**
     * 判断任务是否完成
     * @return
     */
    boolean isComplete();

    /**
     * 获取执行结果
     * @param
     * @return
     */
    R getResult();

}
