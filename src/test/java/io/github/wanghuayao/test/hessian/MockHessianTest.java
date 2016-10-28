package io.github.wanghuayao.test.hessian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.github.wanghuayao.test.hessian.server.ServerConfiguration;
import io.github.wanghuayao.test.hessian.server.shared.HelloWorldService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;


/**
 * MockHessianTest
 * 
 * @author wanghuayao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServerConfiguration.class })
@WebAppConfiguration(value = "/src/main/webapp")
public class MockHessianTest {

    @Autowired
    private WebApplicationContext context;


    @Test
    public void sample() throws Exception {
        MockHessian mockHessian = MockHessianBuilders.hessianContextSetup(context).build();
        HelloWorldService service = mockHessian.uri("/helloworld").create(HelloWorldService.class);

        assertNotNull(service);
        assertEquals("Hello A", service.sayHello("A"));
    }
}
