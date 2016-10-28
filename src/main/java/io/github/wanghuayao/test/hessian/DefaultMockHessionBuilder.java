package io.github.wanghuayao.test.hessian;

import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * DefaultMockHessionBuilder
 * 
 * @author wanghuayao
 */
public class DefaultMockHessionBuilder {

    private DefaultMockMvcBuilder defaultMockMvcBuilder;


    public DefaultMockHessionBuilder(WebApplicationContext context) {
        defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(context);
    }


    public MockHessian build() {
        return new MockHessian(defaultMockMvcBuilder.build());
    }
}
