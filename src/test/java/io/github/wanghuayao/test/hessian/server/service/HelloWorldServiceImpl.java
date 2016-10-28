package io.github.wanghuayao.test.hessian.server.service;

import io.github.wanghuayao.test.hessian.server.shared.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
