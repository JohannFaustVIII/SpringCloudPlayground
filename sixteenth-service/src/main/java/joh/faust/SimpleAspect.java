package joh.faust;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleAspect {

    @Pointcut("execution(* joh.faust.service.SimpleService.getMessage())")
    public void pointcut() {

    }

    @Pointcut("execution(* joh.faust.service.SimpleService.getException())")
    public void exceptionPointcut() {

    }

    @Before("execution(* joh.faust.service.SimpleService.getMessage())")
    public void before() {
        System.out.println("Before point-cut expression logged");
    }

    @AfterReturning(pointcut = "pointcut()")
    public void afterReturning() {
        System.out.println("After returning pointcut targeted logged");
    }

    @After("pointcut()")
    public void afterFinally() {
        System.out.println("After finally pointcut targeted logged");
    }

    @AfterThrowing(pointcut = "exceptionPointcut()")
    public void afterThrowing() {
        System.out.println("After throwing pointcut targeted logged");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around point-cut targeted logged - BEFORE");
        joinPoint.proceed();
        System.out.println("Around point-cut targeted logged - AFTER");
    }



}
