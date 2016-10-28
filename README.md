# test-hessian

spring-test-mvc based Hessian test suite, only suport Hession2.

# Usage
``` java
WebApplicationContext context = ...;
MockHessian mockHessian = MockHessianBuilders.hessianContextSetup(context).build();
// create hessian service
HelloWorldService service = mockHessian.uri("/helloworld").create(HelloWorldService.class);
// call hessian method
String result = service.sayHello("Tom");
...
```
