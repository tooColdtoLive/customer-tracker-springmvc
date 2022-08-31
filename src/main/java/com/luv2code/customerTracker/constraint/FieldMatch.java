package com.luv2code.customerTracker.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldMatchValidator.class)    // define validator class
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})    // where is the annotation applicable
@Retention(RetentionPolicy.RUNTIME) // how long will the annotation be retained, RUNTIME = stored in byte code and apply in runtime
@Documented // to ensure the annotation is shown in the generated JavaDoc
public @interface FieldMatch {
    String first();     // value field

    String second();    // value field

    String message() default "";

    public Class<?>[] groups() default {};    // grouping related constraints

    public Class<? extends Payload>[] payload() default {};    // provides extra information for messages occurred

    @Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }
}
