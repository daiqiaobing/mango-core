package cn.mangowork.core.utils;

import cn.mangowork.core.constant.EnvConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author dailiming
 * @version v1
 * 文件对应的工具信息
 * @create 2018-11-05 11:25
 **/

public class FileUtils {

    private Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 获取文件对象
     * @param path 文件路径，path可以为绝对路径，可以为class目录下的路径
     * @return
     */
    public static File getFileByPath(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()){
            file = new File(EnvConstant.FILE_BASIC_PATH + path);
        }
        if (!file.exists()){
            throw new FileNotFoundException("没有找到对应的文件:" + path);
        }
        return file;
    }

}
