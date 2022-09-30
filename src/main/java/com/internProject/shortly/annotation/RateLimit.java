package com.internProject.shortly.annotation;

import com.internProject.shortly.constants.LimiterMethod;
import com.internProject.shortly.constants.LimiterType;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface RateLimit {


    String key() default "RateLimit";

    String prefix() default "Annotation";

    LimiterMethod limitMethod() default LimiterMethod.PERMITS_BUCKET;

    double permitsPerSecond() default 1.0;

    int period() default 1;

    int permits() default 1;

    LimiterType limitType() default LimiterType.IP;
}
