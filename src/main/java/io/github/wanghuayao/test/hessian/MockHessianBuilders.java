package io.github.wanghuayao.test.hessian;

import org.springframework.web.context.WebApplicationContext;

/**
 * MockHessianBuilders
 * 
 * @author wanghuayao
 */
public class MockHessianBuilders {

    public static DefaultMockHessionBuilder hessianContextSetup(WebApplicationContext context) {
        return new DefaultMockHessionBuilder(context);
    }

}
