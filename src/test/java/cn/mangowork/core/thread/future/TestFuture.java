package cn.mangowork.core.thread.future;

import junit.framework.TestCase;

import java.util.Map;

/**
 * @author dailiming 2018-10-26 17:00
 * @version v1
 * 测试线程future模式
 **/

public class TestFuture extends TestCase {


    public void testFuture() throws InterruptedException {
        MyHandle myHandle = new MyHandle();
        Future<String, String> listFuture = new Future<>(myHandle);
        Map result = listFuture.handle().getResult();

    }

    protected class  MyHandle extends HandleData<String, String>{

        @Override
        void handle() {
            for (int i=0; i< 10; i++){
                super.data.put("结果" + i, i + "");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
