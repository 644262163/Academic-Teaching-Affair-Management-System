package com.niit.controller;

import com.niit.log.Log;
import org.springframework.web.bind.annotation.RequestMapping;

public class TextController {

    @Log(module = "前台", method = "异常页面")
    @RequestMapping("error")
    public String error() throws Exception {
        throw new Exception("异常测试");
    }

}
