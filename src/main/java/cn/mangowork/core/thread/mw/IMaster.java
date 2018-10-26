package cn.mangowork.core.thread.mw;


import java.util.Map;

/**
 * 任务分配
 *
 * @author dailiming 2018-05-08 12:47
 **/

public interface IMaster<K, V> {

    /**
     * 提交任务
     * @param tasks 任务列表
     */
    void submit(Object ...tasks);

    /**
     * 执行任务
     */
    void execute();

    /**
     * 判断任务是否完成
     * @return 是否完成
     */
    boolean isComplete();

    /**
     * 获取执行结果
     * @return 执行结果
     */
    Map<K, V> getResult();

}
