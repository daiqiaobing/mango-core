## jar包对应的接口介绍

### 加载配置文件工具类`EnvMapClient`

​      使用方式

​	需要在项目根目录下面创建`core.xml`,目前只支持`xml`、`yaml`、`property`文件，如下所示：

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration  xmlns="https://github.com/daiqiaobing/mango-core" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="https://github.com/daiqiaobing/mango-core https://github.com/daiqiaobing/mango-core/blob/dev/src/main/resource/mango-core.xsd"  >
    <title>配置文件</title>

    <property>
        <key>aoi</key>
        <value>test.properties</value>
        <type>property</type>
        <scope>global</scope>
    </property>

    <property>
        <key>aoi1</key>
        <value>test.yaml</value>
        <type>yaml</type>
        <scope>global</scope>
    </property>
</configuration>
```

​	其中xml中的key是根据节点名来拼接的

​	获取全局中的数据`EnvMapClient.getXXXByKey`,其中`xxx`为对应的数据类型，支持的数据类型有：`String`、`Int`、`Float`、`List`,参数为对应的key

​	获取全局中的数据`EnvMapClient.getXXXByKey`,其中`xxx`为对应的数据类型，支持的数据类型有：`String`、`Int`、`Float`、`List`，参数`key1`为`core.xml`中配置的key，参数`key2`为对应配置文件中的key




### 多线程调用

#### 多线程Master-worker模式的调用

　　对应的代码在`cn.mangowork.core.thread.mw`目录下；

​	使用介绍，需要创建一个事件处理类(**YourWorker**)，实现**handle**方法，其中**task**为处理的任务，具体类型根据**submit**方法提交的类型决定

​	示例代码如下所示：

```java

public class TestMasterWorker extends TestCase {

    private Logger logger = LoggerFactory.getLogger(TestMasterWorker.class);

    protected class MWorker extends Worker<String, Long>{

        @Override
        protected void handle(Object task) {
            try {
                logger.info("具体任务处理.....................");
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
        }
        for (Map.Entry<String, Long> map: master.getResult().entrySet()){
            logger.info(map.getKey());
            logger.info(map.getValue() + ""); 
        }
        logger.info("执行完成");
    }
}
```


