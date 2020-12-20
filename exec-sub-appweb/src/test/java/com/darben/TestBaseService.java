/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.darben;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试业务基类
 *
 * @author 余兵
 * @version 1.0.0
 * @date 2020/9/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExecSubAppwebApplication.class)
@Configuration
public class TestBaseService {

}
