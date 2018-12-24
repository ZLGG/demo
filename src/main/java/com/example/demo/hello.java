package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

@RestController
@RequestMapping("/hello")
public class hello {
    @RequestMapping("/word/{id}")
    public String world(@PathVariable Long id) {//pathVariable 表示参数的值来自URL路径.
        return "hello word springboot" + id;
    }

    @RequestMapping("/test/{userId}")
    public String test(@PathVariable(value = "userId") Long id) {
        return "test" + id;
    }

    @RequestMapping("Test/{userId}")
    public String Test(@PathVariable(name = "userId") Long id) {
        return "Test" + id;
    }

    @RequestMapping("/test1")
    public String test1(@RequestParam("memberId") Long id) {
        return "test1" + id;
    }

    @RequestMapping(value = "/test2")
    public String test2(@RequestParam(value = "memberId") Long id) {
        return "test2" + id;
    }

    //name=test3不起作用
    @RequestMapping(name = "test3")
    public String test3(@RequestParam(name = "memberId") Long id) {
        return "test3" + id;
    }

    @RequestMapping(value = "test4")
    public String test4(@RequestParam(name = "memberId") Long id) {
        return "test4" + id;
    }

    //只获取默认值
    @RequestMapping("test5")
    public String test5(@RequestParam(defaultValue = "999", required = false) Long id) {
        return "test5" + id;
    }

    @PostMapping("test6")
    public String test6(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();//获取上传文件的名字
        byte[] bytes = file.getBytes();//获取上传文件内容，转为字节数组
        InputStream inputStream = file.getInputStream();//获取一个Inputstream流
        boolean empty = file.isEmpty();//文件上传内容为空，或者就没有文件上传
        long size = file.getSize();//文件上传的大小
        file.transferTo(new File("c:flasfjasfl"));//保存上传文件到目标文件系统
        return null;
    }

}
