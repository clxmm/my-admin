package org.clxmm.autocode.common.spring;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
public @interface QueryMapping {
    @AliasFor(annotation = RequestMapping.class)
    public abstract String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    public abstract String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    public abstract String[] path() default {};

    @AliasFor(annotation = RequestMapping.class)
    public abstract RequestMethod[] method() default RequestMethod.POST;

    @AliasFor(annotation = RequestMapping.class)
    public abstract String[] params() default {};

    @AliasFor(annotation = RequestMapping.class)
    public abstract String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class)
    public abstract String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class)
    public abstract String[] produces() default {};
}

