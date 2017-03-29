package jp.co.allsmart.restbase.core.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { SampleValidImpl.class })
public @interface SampleValid {

    Class<?>[] groups() default {};

    String message() default "{jp.co.allsmart.restbase.validation.constraints.SampleValid.message}";

    Class<? extends Payload>[] payload() default {};

    /**
     * 最小長。
     *
     * @return 最小長
     */
    int min() default 0;

    /**
     * 最大長。
     *
     * @return 最大長
     */
    int max() default Integer.MAX_VALUE;


    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        SampleValid[] value();
    }
}
