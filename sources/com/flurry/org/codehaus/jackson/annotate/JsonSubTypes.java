package com.flurry.org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonSubTypes {

    /* loaded from: classes.dex */
    public @interface Type {
        String name() default "";

        Class value();
    }

    Type[] value();
}
