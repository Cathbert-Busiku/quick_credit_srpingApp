package com.quick_credit.quick_credit.service;

import com.quick_credit.quick_credit.dto.UserDto;
import com.quick_credit.quick_credit.entity.User;

import java.util.List;
public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();


}
