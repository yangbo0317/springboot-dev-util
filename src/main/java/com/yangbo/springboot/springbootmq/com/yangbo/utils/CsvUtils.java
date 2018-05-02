package com.yangbo.springboot.springbootmq.com.yangbo.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * yangbo
 * 2018-05-02
 * springboot-mq
 **/
public class CsvUtils {
    public static void main(String[] args) {
        //csvWrite("/Users/yangbo/Desktop/testcsvwrite.csv");

    }

    public static String csvRead(String filePath) {
        if(StringUtils.isBlank(filePath)){
            return null;
        }
        try {
            CsvReader csvReader = new CsvReader(filePath);
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                // 读一整行
                System.out.println(csvReader.getRawRecord());
                // 读这行的某一列
                System.out.println(csvReader.get("Link"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static String csvWrite(String[] headers,String[] content,String filePath) {
        try {
            // 创建CSV写对象
            CsvWriter csvWriter = getCsvWriter(filePath);
            // 写表头
            csvWriter.writeRecord(headers);
            csvWriter.writeRecord(content);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static CsvWriter getCsvWriter(String filePath){
        CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("UTF-8"));
        return csvWriter;
    }
}
