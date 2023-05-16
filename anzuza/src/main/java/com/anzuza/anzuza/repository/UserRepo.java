package com.anzuza.anzuza.repository;

import com.anzuza.anzuza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User getUserByPhoneNumber(String phoneNumber);
}
