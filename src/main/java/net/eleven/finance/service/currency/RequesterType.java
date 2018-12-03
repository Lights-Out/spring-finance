package net.eleven.finance.service.currency;

import net.eleven.finance.model.RateProvider;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by eleven on 02.12.2018.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,
        ElementType.METHOD,
        ElementType.TYPE,
        ElementType.PARAMETER})
@Qualifier
public @interface RequesterType {
    RateProvider provider();
}
