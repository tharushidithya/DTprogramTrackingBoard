package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.user.UserEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Configuration
@Service
@ComponentScan
public interface UserService {


    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUser(Long id);

    User updateUser(Long id, UserEntity userEntity);

    UserEntity createUser(UserEntity userEntity);
}
