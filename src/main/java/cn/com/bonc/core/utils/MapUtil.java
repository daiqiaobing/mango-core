package cn.com.bonc.core.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map操作的相关工具
 *
 * @author
 * @create 2018-05-14 16:43
 **/

public class MapUtil {

    /**
     * map中的数据格式为<String, List>,向List中添加数据
     * @param map map集合
     * @param key 索引
     * @param data 数据
     */
    public static void addListData(Map<String, List> map, String key, Object data){
        if (map.containsKey(key)){
            map.get(key).add(data);
        }else {
            LinkedList<Object> objects = new LinkedList<>();
            objects.add(data);
            map.put(key, objects);
        }
    }

}
