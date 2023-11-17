package com.academy.spring_lv4.auth;

import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.entity.UserRoleEnum;
import com.academy.spring_lv4.jwt.JwtUtil;
import com.academy.spring_lv4.repository.UserRepository;

import com.academy.spring_lv4.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Objects;

import static com.academy.spring_lv4.jwt.JwtUtil.AUTHORIZATION_KEY;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        boolean hasAnnottion = checkAnnotation(handler, Auth.class);

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserRoleEnum userRole = userRepository.findByEmail(userEmail).orElse(null).getAuth();
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (hasAnnottion &&
                handlerMethod.getMethodAnnotation(Auth.class).role() == userRole) {
            return true;
        }
        log.info("관리자만 접근 가능한 페이지 입니다.");
        response.sendError(401, "관리자만 접근 가능한 페이지 입니다.");
        return false;
    }

    private boolean checkAnnotation(Object handler, Class<Auth> authClass) {
        if (handler instanceof HandlerMethod) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (null != handlerMethod.getMethodAnnotation(authClass) ||
                null != handlerMethod.getBeanType().getAnnotation(authClass)) {
            return true;
        }
        return false;

    }


}
