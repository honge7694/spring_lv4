package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.user.SignupRequestDto;
import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.entity.UserRoleEnum;
import com.academy.spring_lv4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        char gender = requestDto.getGender();
        String phoneNumber = requestDto.getPhoneNumber();
        String address = requestDto.getAddress();
        UserRoleEnum role = requestDto.getAuth();

        // 이메일 중복 확인
        userRepository.findByEmail(email)
                .ifPresent((existedUser) -> {throw new IllegalArgumentException("에러 발생");});

        // 유저 생성
//      TODO: requestDto 수정 User user = new User(requestDto)
        User user = new User(email, password, gender, phoneNumber, address, role);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입을 성공했습니다.");
    }
}
