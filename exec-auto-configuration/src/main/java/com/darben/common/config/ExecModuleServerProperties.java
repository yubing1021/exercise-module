/*
 * xxx股份有限公司版权所有
 */
package com.darben.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * exercise配置信息属性类
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/12/20
 */
@ConfigurationProperties(prefix = "exercise-config")
public class ExecModuleServerProperties {

    private static final String ROOT_NAME = "exercise-module";

    private String rootName = ROOT_NAME;

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }
}
