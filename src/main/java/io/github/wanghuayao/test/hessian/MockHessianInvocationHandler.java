package io.github.wanghuayao.test.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianProtocolException;

/**
 * MockHessianInvocationHandler
 * 
 * @author wanghuayao
 */
public class MockHessianInvocationHandler implements InvocationHandler {

    private MockMvc mockMvc;
    private String  uri;


    MockHessianInvocationHandler(MockMvc mockMvc, String uri) {
        this.mockMvc = mockMvc;
        this.uri = uri;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        AbstractHessianOutput out = new Hessian2Output(bao);
        out.call(method.getName(), args);
        out.flush();

        MockHttpServletResponse response = mockMvc
                .perform(
                        MockMvcRequestBuilders.post(uri).contentType("x-application/hessian")
                                .content(bao.toByteArray())).andReturn().getResponse();

        try (ByteArrayInputStream bis = new ByteArrayInputStream(response.getContentAsByteArray())) {
            return retriveResult(method.getReturnType(), bis);
        }
    }


    private Object retriveResult(Class<?> returnType, InputStream is) throws Throwable {

        AbstractHessianInput in;

        int code = is.read();

        if (code == 'H') {
            int major = is.read();
            int minor = is.read();
            if (major != 0x02) {
                throw new IOException("Version " + major + "." + minor + " is not understood");
            }

            in = getHessian2Input(is);
            return in.readReply(returnType);

        } else if (code == 'r') {
            throw new HessianProtocolException("目前只支持Hessian2");
        }
        throw new HessianProtocolException("'" + (char) code + "' is an unknown code");
    }


    private AbstractHessianInput getHessian2Input(InputStream is) {
        AbstractHessianInput in = new Hessian2Input(is);
        return in;
    }
}
