package joh.faust;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SimpleAspect {

    @Pointcut("execution(* joh.faust.service.SimpleService.*(..))")
    public void pointcut() {

    }

    @Pointcut("execution(* joh.faust.service.SimpleService.*(..)) && args(a, b)")
    public void pointcut2(int a, String[] b) {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        logArgs(joinPoint.getArgs(), "BEFORE");
        System.out.println("Before point-cut expression logged");
    }

    @Before("pointcut2(a, b)")
    public void beforeGetArgs(int a, String[] b) {
        System.out.println("Before advice triggered - args case");
        System.out.println("A = " + a);
        System.out.println("B = " + Arrays.toString(b));
    }

    @AfterReturning(pointcut = "pointcut()", returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        System.out.println("After returning pointcut targeted logged");
        logArgs(joinPoint.getArgs(), "AFTER RETURNING");
        System.out.println("RETURNED: " + retVal);
    }

    @After("pointcut()")
    public void afterFinally(JoinPoint joinPoint) {
        logArgs(joinPoint.getArgs(), "AFTER");
        System.out.println("After finally pointcut targeted logged");
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("After throwing pointcut targeted logged");
        logArgs(joinPoint.getArgs(), "AFTER THROWING");
        System.out.println("THROWN: " + ex);
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around point-cut targeted logged - BEFORE");
        logArgs(joinPoint.getArgs(), "AROUND");
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            System.out.println("Around point-cut targeted logged - AFTER");
        }
        System.out.println("AROUND RETURNED: " + result);
        return result;
    }

    private void logArgs(Object[] args, String prefix) {
        if (args != null && args.length > 0) {
            System.out.println(prefix + ": Arguments:");
            for (Object arg : args) {
                System.out.println("\t " + arg);
            }
        }
    }

}
