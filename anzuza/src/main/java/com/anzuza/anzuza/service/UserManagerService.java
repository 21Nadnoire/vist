package com.anzuza.anzuza.service;

import com.anzuza.anzuza.model.User;

import java.io.IOException;
import java.util.Optional;

public interface UserManagerService {

    User createUser(User user);
    Optional<User> getUserById(Long userId);
//    User toSendEmail(User user);
    String authenticateUser(User user);
//    boolean authenticateUserByEmail(User user);
    void sendOtp(String phoneNumber) throws IOException;
}
