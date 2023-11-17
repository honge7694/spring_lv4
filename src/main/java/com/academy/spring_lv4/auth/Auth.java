package com.academy.spring_lv4.auth;

import com.academy.spring_lv4.entity.UserRoleEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {
    UserRoleEnum role() default UserRoleEnum.ADMIN;

}
