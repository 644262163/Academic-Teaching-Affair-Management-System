package com.niit.controller;

import com.niit.log.Log;
import org.springframework.web.bind.annotation.RequestMapping;

public class TextController {

    @Log(module = "ǰ̨", method = "�쳣ҳ��")
    @RequestMapping("error")
    public String error() throws Exception {
        throw new Exception("�쳣����");
    }

}
