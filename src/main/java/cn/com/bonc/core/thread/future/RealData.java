package cn.com.bonc.core.thread.future;



/**
 * 文件中的数据
 * T 为处理的结果
 * @author
 * @create 2018-05-15 13:43
 **/

public class RealData <R> implements BaseData<R>{

    protected R results;

    @Override
    public R getResult() {
        return this.results;
    }

    @Override
    public void handle() {

    }

}
