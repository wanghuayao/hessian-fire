package io.github.wanghuayao.test.hessian.server;

import io.github.wanghuayao.test.hessian.server.service.HelloWorldServiceImpl;
import io.github.wanghuayao.test.hessian.server.shared.HelloWorldService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * TestServerConfiguration
 * 
 * @author wanghuayao
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.github.wanghuayao.test.hessian.server", useDefaultFilters = false, includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class ServerConfiguration extends WebMvcConfigurationSupport {

    @Bean(name = "/helloworld")
    public HessianServiceExporter ss() {
        //        <bean name="/queryTask" class="com.meizu.cetus.webide2.hession.AutoCloseableHessianServiceExporter">
        //    <!--    <bean name="/queryTask" class="org.springframework.remoting.caucho.HessianServiceExporter"> -->
        //            <property name="service" ref="sharedQueryTaskServiceImpl"/>
        //            <property name="serviceInterface" value="com.meizu.cetus.webide2.share.hessian.SharedQueryTaskService"/>
        //           
        //        </bean>

        HessianServiceExporter exporter = new HessianServiceExporter();

        exporter.setService(new HelloWorldServiceImpl());
        exporter.setServiceInterface(HelloWorldService.class);

        return exporter;
    }
}
