package cn.com.bonc.core.reflect;

import cn.com.bonc.core.beans.ObjectLoader;
import cn.com.bonc.core.dto.PoiDTO;
import cn.com.bonc.core.utils.ArrayUtil;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试使用缓存与不是用缓存的相差效果
 *
 * @author
 * @create 2018-06-25 11:54
 **/

public class TestReflect extends TestCase {

    private Logger logger = LoggerFactory.getLogger(TestReflect.class);

    private int testCount = 1000000;

    /**
     * 测试使用反射，不使用反射，使用缓存以及不是用缓存的运行结果
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    public void testReflect() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        List<PoiDTO> poiBeans2 = useCache(PoiDTO.class);
        List<PoiDTO> poiDTOS = withoutReflect();
        List<PoiDTO> poiBeans1 = withoutCache(PoiDTO.class);
        /**
         * (循环1千万次)运行结果为：
         *          2018-06-25 14:19:27 [INFO] 使用缓存的时间花费是：19559
         *          2018-06-25 14:19:30 [INFO] 没有使用反射的时间花费是：3167
         *          2018-06-25 14:21:28 [INFO] 没有使用缓存的时间花费是：117722
         */

    }


    public void testReflectObj() throws Exception {
        String[] data = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31","32","33", "34","35","36","37","38","39",
                "40","41","42","43","44","45","46","47","48","49","50","51","52","53"};
        List<PoiDTO> poiDTOS = withoutReflectObj(data);
        List<PoiDTO> poiBeans2 = useCacheObj(PoiDTO.class, data);
        List<PoiDTO> poiBeans1 = withoutCacheObj(PoiDTO.class, data);
        /**
         * (循环1千万次)运行结果为：
         *          2018-06-25 14:19:27 [INFO] 使用缓存的时间花费是：19559
         *          2018-06-25 14:19:30 [INFO] 没有使用反射的时间花费是：3167
         */

    }

    private List<PoiDTO> withoutCacheObj(Class<PoiDTO> poiDTOClass, String[] data) throws Exception {
        long start = System.currentTimeMillis();
        List<PoiDTO> poiBeans = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            PoiDTO poiDTO = ArrayUtil.arrayToObject(poiDTOClass, data);
        }
        long end = System.currentTimeMillis();
        logger.info("没有使用缓存的时间花费是：{}", end - start);
        return poiBeans;
    }

    private List<PoiDTO> withoutReflectObj(String[] data) {
        long start = System.currentTimeMillis();
        List<PoiDTO> poiBeans = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            PoiDTO poiDTO = new PoiDTO();
            for (int j = 0; j < data.length; j++) {
                poiDTO.setAddress(data[j]);
            }
        }
        long end = System.currentTimeMillis();
        logger.info("没有使用反射的时间花费是：{}", end - start);
        return poiBeans;
    }

    private List<PoiDTO> useCacheObj(Class<PoiDTO> poiDTOClass, String[] data) throws Exception {
        long start = System.currentTimeMillis();
        List<PoiDTO> poiBeans = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            PoiDTO bean = ObjectLoader.getBean(poiDTOClass, data);
        }
        long end = System.currentTimeMillis();
        logger.info("使用缓存的时间花费是：{}", end - start);
        return poiBeans;
    }

    /**
     * 不使用缓存
     */
    private List<PoiDTO>  withoutReflect() {
        long start = System.currentTimeMillis();
        List<PoiDTO> poiBeans = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            PoiDTO poiDTO = new PoiDTO();
            poiDTO.setAddress("王五" + i);
            // poiBeans.add(poiDTO);
        }
        long end = System.currentTimeMillis();
        logger.info("没有使用反射的时间花费是：{}", end - start);
        return poiBeans;
    }

    /**
     * 使用缓存
     */
    private <T> List<T>  withoutCache(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        long start = System.currentTimeMillis();
        List<T> poiBeans = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            T t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields){
                if ("address".equals(field.getName())){
                    field.setAccessible(true);
                    field.set(t, "张三" + i);
                }
            }
            // poiBeans.add(t);
        }
        long end = System.currentTimeMillis();
        logger.info("没有使用缓存的时间花费是：{}", end - start);
        return poiBeans;
    }

    /**
     * 使用缓存
     */
    private <T> List<T> useCache(Class<T> clazz) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        long start = System.currentTimeMillis();
        List<T> poiBeans = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            T t = clazz.newInstance();
            Field address = ReflectCache.getDeclaredField(clazz, "address");
            address.setAccessible(true);
            address.set(t, "李四" + i);
            // poiBeans.add(t);
        }
        long end = System.currentTimeMillis();
        logger.info("使用缓存的时间花费是：{}", end - start);
        return poiBeans;
    }

    public void testLogData(){
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 54; i++) {
            str.append( "\"" + i + "\",");
        }
        logger.info(str.toString());
    }
    
}
