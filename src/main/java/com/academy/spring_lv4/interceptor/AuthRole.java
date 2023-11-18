package com.academy.spring_lv4.interceptor;


import com.academy.spring_lv4.entity.UserRoleEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 메서드에 적용(메서드만 사용가능)
@Retention(RetentionPolicy.RUNTIME) // 런타임시에도 어노테이션 정보 유지
public @interface AuthRole {
    UserRoleEnum role() default UserRoleEnum.ADMIN;
}
