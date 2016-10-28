package io.github.wanghuayao.test.hessian;

import java.lang.reflect.Proxy;

import org.springframework.test.web.servlet.MockMvc;

/**
 * MockHessian
 * 
 * <pre class="code">
 * WebApplicationContext wac = ...;
 * MockHessian mockHessian = MockHessianBuilders.hessianContextSetup(wac).build();
 * 
 * // instance hession service
 * SERVICE service  mockHessian.uri(URI).create(SERVICE.class);
 * 
 * // call service
 * service.methodA();
 * </pre>
 * 
 * @author wanghuayao
 */
public class MockHessian {

    private MockMvc mockMvc;


    public MockHessian(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    public Creater uri(String uri) {
        return new Creater(mockMvc, uri);
    }

    public static class Creater {
        private MockMvc mockMvc;
        private String  uri;


        public Creater(MockMvc mockMvc, String uri) {
            this.mockMvc = mockMvc;
            this.uri = uri;
        }


        @SuppressWarnings("unchecked")
        public <T> T create(Class<T> clazz) {
            Object serivce = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz },
                    new MockHessianInvocationHandler(mockMvc, uri));
            return (T) serivce;
        }
    }
}
