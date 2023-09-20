package joh.faust;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleAspect {

    @Pointcut("execution(* joh.faust.service.SimpleService.*())")
    public void pointcut() {

    }

    @Before("pointcut()")
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

    @AfterThrowing(pointcut = "pointcut()")
    public void afterThrowing() {
        System.out.println("After throwing pointcut targeted logged");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around point-cut targeted logged - BEFORE");
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            System.out.println("Around point-cut targeted logged - AFTER");
        }
        return result;
    }



}
