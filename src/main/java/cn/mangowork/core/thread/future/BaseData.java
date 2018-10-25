package cn.mangowork.core.thread.future;


/**
 * @author Administrator
 */
public interface BaseData<R> {

    /**
     * 获取结果
     * @exception InterruptedException
     * @return R
     */
    R getResult() throws InterruptedException;

    /**
     * 程序的处理
     */
    void handle();
}
