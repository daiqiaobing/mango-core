package cn.com.bonc.core.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 使用POI操作csv的文件
 *
 * @author
 * @create 2018-05-07 14:09
 **/

public class CsvUtil {

    /**
     * 创建创建CSV文件
     */
    public static void createCsvFile(String fileName, Set data, String[] properties, String delimiter) throws Exception {
        boolean writeHead = false;
        File file = new File(fileName);
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file));
        try {
            for(Object obj : data){
                //利用反射获取所有字段
                Field[] fields = obj.getClass().getDeclaredFields();
                List<Field> sameField = getSameField(properties, fields);
                if (!writeHead){
                    writeHead(os, sameField, delimiter);
                    writeHead = true;
                }
                writeBody(os, sameField, delimiter, obj);
            }
        }finally {
            os.flush();
            os.close();
        }
    }


    private static void writeHead(OutputStreamWriter os, List<Field> fields, String delimiter) throws IOException {
        StringBuilder head = new StringBuilder();
        for (int i=0; i<fields.size(); i++){
            head.append(FieldsUtil.getDesc(fields.get(i)));
            if (i < (fields.size() - 1)){
                head.append(delimiter);
            }
        }
        head.append("\n");
        os.write(head.toString());
    }


    private static void writeBody(OutputStreamWriter os, List<Field> fields, String delimiter, Object cur) throws IOException, IllegalAccessException {
        StringBuilder value = new StringBuilder();
        for (int i=0; i<fields.size(); i++){
            fields.get(i).setAccessible(true);
            Object rs = fields.get(i).get(cur);
            rs = rs == null ? "" : rs.toString();
            value.append(rs);
            if (i < (fields.size() - 1)){
                value.append(delimiter);
            }
        }
        value.append("\n");
        os.write(value.toString());
    }

    /**
     * 创建创建CSV文件
     */
    public static void createCsvFile(String fileName, Set data, String delimiter, boolean... useHead) throws IOException, IllegalAccessException {
        boolean writeHead = false;
        File file = new File(fileName);
        boolean flag = useHead.length == 1 ? useHead[0] : true;
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file));
        try {
            for(Object obj : data){
                //利用反射获取所有字段
                Field[] fields = obj.getClass().getDeclaredFields();
                List<Field> sameField = FieldsUtil.getUseField(fields);
                if (!writeHead && flag){
                    writeHead(os, sameField, delimiter);
                    writeHead = true;
                }
                writeBody(os, sameField, delimiter, obj);
            }
        }finally {
            os.flush();
            os.close();
        }
    }


    /**
     * 创建创建CSV文件
     */
    public static void createCsvFile(String fileName, List<String> data) throws IOException {
        File file = new File(fileName);
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file));
        try {
            for(String obj : data){
                os.write(obj + "\n");
            }
        }finally {
            os.flush();
            os.close();
        }
    }


    private static List<Field> getSameField(String[] properties, Field[] fields) throws Exception {
        List<Field> sameField = new LinkedList<>();
        if (properties == null){
            throw new Exception("the param properties can't be empty!");
        }
        for(String property : properties){
            for(Field field : fields){
                if (property.equals(field.getName())){
                    sameField.add(field);
                    break;
                }
            }
        }
        if (sameField.size() == 0){
            throw new Exception("can't find the same field");
        }
        return sameField;
    }

}
