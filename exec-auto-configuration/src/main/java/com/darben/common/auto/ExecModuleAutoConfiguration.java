/*
 * xxx股份有限公司版权所有
 */
package com.darben.common.auto;

import com.darben.common.component.ExecRootFrameworkServer;
import com.darben.common.config.ExecModuleServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * exercise模块框架自动配置类
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/12/20
 */
@Configuration
@EnableConfigurationProperties(ExecModuleServerProperties.class)
@ConditionalOnClass(ExecRootFrameworkServer.class)
@ConditionalOnProperty(prefix = "exec-config", value = "enabled", matchIfMissing = true)
public class ExecModuleAutoConfiguration {

    @Autowired
    private ExecModuleServerProperties execModuleServerProperties;

    /**
     * 自动装载ExecRootFrameworkServer
     * <p>
     * 当ExecRootFrameworkServer bean不存在的时候，进行装载
     *
     * @author darben
     * @date 2020/12/20 21:14
     * @version 1.0.0
     */
    @Bean
    @ConditionalOnMissingBean(ExecRootFrameworkServer.class)
    public ExecRootFrameworkServer execRootFrameworkServer() {
        ExecRootFrameworkServer execSubAppWebServer = new ExecRootFrameworkServer();
        execSubAppWebServer.setName(execModuleServerProperties.getRootName());
        return execSubAppWebServer;
    }
}
