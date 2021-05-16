package com.me.util;

import com.me.entity.Department;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 文件工具类
 */
public class FileUtils {
    /**
     * 解析 list文件
     *
     * @return 数据实体
     * @throws JAXBException         解析异常
     * @throws FileNotFoundException 文件未找到异常
     */
    public static Department getDepartment(String path) throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance(Department.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Department department = (Department) unmarshaller.unmarshal(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        return department;
    }

    /**
     * 从配置文件中读取主要配置
     *
     * @param fileName 文件路径
     * @return map集合
     * @throws IOException IOException
     */
    public static Map<String, String> parseConfig(String fileName) throws IOException {
        Map<String, String> result = new HashMap<>();
        Properties properties = new Properties();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        properties.load(bufferedReader);

        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = properties.getProperty(key);
            result.put(key, value);
        }
        return result;
    }
}
