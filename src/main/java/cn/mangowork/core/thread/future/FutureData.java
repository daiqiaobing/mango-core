package cn.mangowork.core.thread.future;

/**
 * @author
 * @create 2018-05-15 13:41
 **/

public class FutureData<R, T extends RealData<R>> implements BaseData<R>{

    private T realData;

    private boolean isReady = false;

    public synchronized  void setRealData(T realData) {
        if (isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    public T getRealData() {
        return this.realData;
    }

    @Override
    public synchronized R getResult() throws InterruptedException {
        if (!isReady){
            wait();
        }
        return this.realData.getResult();
    }

    @Override
    public void handle() {

    }


}
