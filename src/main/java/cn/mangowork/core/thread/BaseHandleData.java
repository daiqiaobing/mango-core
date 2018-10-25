package cn.mangowork.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Administrator
 */
public interface BaseHandleData<T> extends Runnable {

    Logger logger = LoggerFactory.getLogger("logFile");

    void handle(T task) throws Exception;

}
