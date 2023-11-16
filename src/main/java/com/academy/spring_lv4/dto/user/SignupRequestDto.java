package com.academy.spring_lv4.dto.user;

import com.academy.spring_lv4.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상 최대 15자 이하이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;
    private char gender;
    private String phoneNumber;
    private String address;
    private UserRoleEnum auth;
}
