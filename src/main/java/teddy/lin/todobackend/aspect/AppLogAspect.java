package teddy.lin.todobackend.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;

@Slf4j
@Order(-5)
@Aspect
@Component
public class AppLogAspect {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(* teddy.lin.todobackend.controller..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("Request URL: {}", request.getRequestURL().toString());
        log.info("Request Method: {}", request.getMethod());
        log.info("IP: {}", request.getRemoteAddr());
        log.info("Class Method:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("Cookies:{}", (Object[]) request.getCookies());
        log.info("Params:{}", Arrays.toString(joinPoint.getArgs()));

        Enumeration<String> enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = enums.nextElement();
            log.info("{}:{}", paraName, request.getParameter(paraName));
        }
    }


    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("doAfter():{}", joinPoint.toString());
    }

    @AfterReturning(value = "pointcut()", returning = "rvt")
    public void doAfterReturning(JoinPoint joinPoint, Object rvt) {
        log.info("time:{} ms", ((System.currentTimeMillis() - threadLocal.get())));
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("Class Method:{}.{}", method.getDeclaringClass().getName(), method.getName());
        log.info("Response:{}", rvt);
        threadLocal.remove();
    }
}

