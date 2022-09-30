package com.internProject.shortly.RateLimiter;

public interface RateLimiter {

    boolean isRateLimited(String key, RateLimiterVariable rateLimiterVariables);
}
