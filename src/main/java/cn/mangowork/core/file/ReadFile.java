package cn.mangowork.core.file;

import cn.mangowork.core.beans.ObjectLoader;
import cn.mangowork.core.properties.PropertiesConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件中的数据
 *
 * @author
 * @create 2018-06-15 16:22
 **/

public class ReadFile {


    /**
     * 读取文件，并且将数据转换为对象
     * @param filePath 文件路径
     * @param clazz class类
     * @param <T> 范型
     * @return 读取文件的结果
     */
    public static <T> List<T> getBeans(String filePath, Class<T> clazz) throws Exception {
        String encode = PropertiesConfig.getValueByKey("file_read_encode", "utf-8");
        String delimiter = PropertiesConfig.getValueByKey("file_delimiter", "\t");
        return getBeans(filePath, clazz, encode, delimiter);
    }

    /**
     * 读取文件
     * @param filePath
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeans(String filePath, Class<T> clazz, String encode, String delimiter) throws Exception {
        return getBeans(new File(filePath), clazz, encode, delimiter);
    }

    /**
     * 读取文件
     * @param file 文件对象
     * @param clazz class类
     * @param <T> 范型
     * @return 将文件中的数据转换为对象
     */
    public static <T> List<T> getBeans(File file, Class<T> clazz, String encode, String delimiter) throws Exception {
        ArrayList<T> result = new ArrayList<>();
        LineIterator lineIterator = FileUtils.lineIterator(file, encode);
        while (lineIterator.hasNext()){
            String line = lineIterator.nextLine();
            String[] split = line.split(delimiter, -1);
            T bean = ObjectLoader.getBean(clazz, split);
            result.add(bean);
        }
        if (lineIterator != null){
            lineIterator.close();
        }
        return result;
    }

    /**
     * 读取文件
     * @param in 输入流
     * @param clazz class类
     * @param <T> 范型
     * @return 将输入流转换为对象
     */
    public static <T> List<T> getBeans(InputStream in, Class<T> clazz, String encode, String delimiter) throws Exception {
        ArrayList<T> result = new ArrayList<>();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            String line = new String(buffer);
            String[] split = line.split(delimiter, -1);
            T bean = ObjectLoader.getBean(clazz, split);
            result.add(bean);
        }
        if (in != null){
            in.close();
        }
        return result;
    }

}
