package cn.mangowork.core.thread.mw;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author dailiming
 * @version v1
 * 多线程测试Master-worker模式
 * @create 2018-10-26 13:11
 **/

public class TestMasterWorker extends TestCase {

    private Logger logger = LoggerFactory.getLogger(TestMasterWorker.class);


    protected class MWorker extends Worker<String, Long>{

        @Override
        protected void handle(Object task) {
            try {
                logger.info("任务处理中.....................");
                super.results.put(String.valueOf(task.hashCode()), System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testMasterWorker(){
        Master<String, Long> master = new Master<>(new MWorker(), 12);
        for (int i=0; i< 100; i++){
            master.submit("task" + i);
        }
        master.execute();
        while (!master.isComplete()){
            continue;
        }
        for (Map.Entry<String, Long> map: master.getResult().entrySet()){
            logger.info(map.getKey());
            logger.info(map.getValue() + "");
            assertNotNull(map.getKey());
            assertNotNull(map.getValue());
        }
        logger.info("执行完成");
    }
}
