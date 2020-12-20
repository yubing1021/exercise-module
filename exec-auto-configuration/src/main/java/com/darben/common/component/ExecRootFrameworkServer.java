/*
 * xxx股份有限公司版权所有
 */
package com.darben.common.component;

/**
 * exec-sub-appweb模块服务类
 * <p>
 * 自动装载实例化的服务类
 * </p>
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/12/20
 */
public class ExecRootFrameworkServer {

    private String name;

    public String sayName() {
        return "this root server name is " + name;
    }

    public void init() {
        // TODO
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
