/*
 * xxx股份有限公司版权所有
 */
package com.darben.controller;

import com.darben.common.component.ExecRootFrameworkServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/12/20
 */
@RestController
public class TestController {

    @Autowired
    private ExecRootFrameworkServer execSubAppWebServer;

    @RequestMapping("/")
    public Object index() {
        return execSubAppWebServer.sayName();
    }
}
