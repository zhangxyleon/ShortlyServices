package com.internProject.shortly.RateLimiter;

//import com.google.common.cache.LoadingCache;
//import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateLimiterExecutor {
    private final PermitsBucketRateLimiter permitsBucketRateLimiter;

    @Autowired
    public RateLimiterExecutor(PermitsBucketRateLimiter permitsBucketRateLimiter) {
        this.permitsBucketRateLimiter = permitsBucketRateLimiter;
    }

    public PermitsBucketRateLimiter getPermitsBucketRateLimiter() {
        return permitsBucketRateLimiter;
    }
    //    public static boolean isRateLimited(RateLimiter rateLimiter, int period, int permits) {
//        boolean tryAcquire = rateLimiter.tryAcquire(permits, (long) period, TimeUnit.SECONDS);
//        return !tryAcquire;
//    }
}

