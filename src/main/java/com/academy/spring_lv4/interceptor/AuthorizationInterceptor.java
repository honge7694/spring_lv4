package com.academy.spring_lv4.interceptor;

import com.academy.spring_lv4.entity.UserRoleEnum;
import com.academy.spring_lv4.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Slf4j(topic = "Authorization Interceptor")
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 메서드에 AuthRole 어노테이션 존재 확인 ||
        // 현재 처리 중인 요청을 담당하는 핸들러 메서드가 소속된 컨트롤러 클래스에 AuthRole 어노테이션이 있는지 여부를 확인한다
        if (method.isAnnotationPresent(AuthRole.class) || handlerMethod.getBeanType().isAnnotationPresent(AuthRole.class)) {
            // header에서 token 가져오기
            String token = jwtUtil.getJwtFromHeader(request);

            if(!jwtUtil.validateToken(token)) {
                log.error("not validate token");
                return false;
            }

            // 사용자 정보 추출
            Claims claims = jwtUtil.getUserInfoFromToken(token);
            String username = claims.getSubject();
            String role = (String) claims.get("auth");
            log.info("{}의 권한 : {}", username, role);

            if(!StringUtils.equals(role, "ADMIN")) {
                log.error("권한이 없습니다.");
                return false;
            }
        }

        return true;
    }
}
