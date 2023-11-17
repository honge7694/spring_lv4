package com.academy.spring_lv4.dto.user;

import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String email;
    private char gender;
    private String phoneNumber;
    private String address;
    private UserRoleEnum role;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.role = user.getRole();
    }
}
