package com.internProject.shortly.RateLimiter;


import com.internProject.shortly.annotation.RateLimit;
import com.internProject.shortly.constants.LimiterMethod;
import com.internProject.shortly.constants.LimiterType;
import com.internProject.shortly.util.ClientIPUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.internProject.shortly.constants.LimiterMethod.PERMITS_BUCKET;
import static com.internProject.shortly.constants.LimiterType.IP;

@Aspect
@Configuration
public class RateLimiterProcessor {


    private final RateLimiterExecutor rateLimiterExecutor;

    @Autowired
    public RateLimiterProcessor(RateLimiterExecutor rateLimiterExecutor) {
        this.rateLimiterExecutor = rateLimiterExecutor;
    }

    @Around("execution(public * *(..)) && @annotation(com.internProject.shortly.annotation.RateLimit)")
    public Object processor(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method == null) {
            return null;
        } else {
            RateLimit rateLimitAnnotation = method.getAnnotation(RateLimit.class);
            LimiterType limitType = rateLimitAnnotation.limitType();
            String key = getLimiterKey(request, method, rateLimitAnnotation, limitType);
            key = StringUtils.join(rateLimitAnnotation.prefix(), key);

            double permitsPerSecond = rateLimitAnnotation.permitsPerSecond();
            int period = rateLimitAnnotation.period();
            int permits = rateLimitAnnotation.permits();
            //LimiterMethod limitMethod = rateLimitAnnotation.limitMethod();

            RateLimiterVariable rateVariable = new RateLimiterVariable();
            rateVariable.setKey(key);
            rateVariable.setPermits(permits);
            rateVariable.setPeriod(period);
            rateVariable.setPermitsPerSecond(permitsPerSecond);
            RateLimiter rateLimiter =  rateLimiterExecutor.getPermitsBucketRateLimiter();

            if (rateLimiter.isRateLimited(key, rateVariable)) {
                System.out.println("Access to " + method.getName() + " from " + key+ " is rate limited");
                sendFallback();
                return null;
            }
        }

        return joinPoint.proceed();
    }


    private String getLimiterKey(HttpServletRequest request, Method method, RateLimit rateLimiterAnnotation, LimiterType limiterType) {
        String key = StringUtils.upperCase(
                method.getDeclaringClass().getSimpleName() + ":" + method.getName()
        );
        return key + "_" + LimiterType.IP + ":" + ClientIPUtils.getClientIpAddress(request);

    }

    private void sendFallback() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        System.out.println("TOO MANY REQUESTS");
    }
}
