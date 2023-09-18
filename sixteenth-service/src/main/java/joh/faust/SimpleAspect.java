package joh.faust;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleAspect {

    @Pointcut("execution(* joh.faust.service.SimpleService.getMessage())")
    public void pointcut() {

    }

    @Before("execution(* joh.faust.service.SimpleService.getMessage())")
    public void before() {
        System.out.println("Before point-cut expression logged");
    }

    @AfterReturning(pointcut = "pointcut()")
    public void afterReturning() {
        System.out.println("After returning pointcut targeted logged");
    }

}
