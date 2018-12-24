package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication  extends SpringBootServletInitializer {


    public static void main(String[] args) throws Exception{
        SpringApplication.run(DemoApplication.class, args);
        /*List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        //集合转数组
        String[] strings = list.toArray(new String[0]);
        for (int j = 0; j < 10; j++) {
            System.out.println(strings[j]);
        }
        //数组转list
        List<String> list1 = Arrays.asList(strings);*/
        String ddl = "";
        //esMappingsUtil.createMappings(ddl);
        System.out.println("success");
    }



}
