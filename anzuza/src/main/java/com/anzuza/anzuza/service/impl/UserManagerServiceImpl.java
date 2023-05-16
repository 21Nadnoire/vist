package com.anzuza.anzuza.service.impl;


import com.anzuza.anzuza.Util.SendRequestUtil;
import com.anzuza.anzuza.Util.VerifyCodeUtil;
import com.anzuza.anzuza.model.User;
import com.anzuza.anzuza.repository.UserRepo;
import com.anzuza.anzuza.service.UserManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagerServiceImpl implements UserManagerService {

    private final UserRepo userRepo;

    @Override
    public User createUser(User user) {
        Integer otp = Integer.valueOf(VerifyCodeUtil.generateVerifyCode(4,"0123456789"));
        user.setOtp(otp);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

//    @Override
//    public User toSendEmail(User user) {
//        User getuser = userRepo.getUserByEmail(user.getEmail());
//        return getuser;
//    }

    @Override
    public String authenticateUser(User user) {
        User savedUser = userRepo.getUserByPhoneNumber(user.getPhoneNumber());
        if(savedUser == null){
            return null;
        }
        if(savedUser.getOtp().equals(user.getOtp())){
            return String.valueOf(savedUser.getUserId());
        }
        return null;
    }

//    @Override
//    public boolean authenticateUserByEmail(User user) {
//        String requestMail="";
//        String requestPassword="";
//        User userEmail = userRepo.getUserByEmail(user.getEmail());
//        User userPassword = userRepo.getUserByEmail(user.getEmail());
//        if(userEmail.equals(requestMail) && userPassword.equals(requestPassword)) {
//            return true;
//        }else {
//            return false;
//        }
//    }


    @Override
    public void sendOtp(String phoneNumber) throws IOException {
        User savedUser = userRepo.getUserByPhoneNumber(phoneNumber);
        String otp = VerifyCodeUtil.generateVerifyCode(4,"0123456789");
        savedUser.setOtp(Integer.valueOf(otp));
        SendRequestUtil.sendPhoneNumberVerificationCode(phoneNumber,otp);
        userRepo.save(savedUser);
    }
}
