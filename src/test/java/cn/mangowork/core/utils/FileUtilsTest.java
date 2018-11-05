package cn.mangowork.core.utils;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class FileUtilsTest extends TestCase {

    
    public void testGetFileByPath() throws FileNotFoundException {
        String path = "file\\core.xml";
        File fileByPath = FileUtils.getFileByPath(path);
    }
    
}