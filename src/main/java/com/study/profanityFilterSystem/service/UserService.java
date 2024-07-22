package com.study.profanityFilterSystem.service;

import com.study.profanityFilterSystem.entity.Users;
import com.study.profanityFilterSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users saveUser(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("UserName cannot be null or empty");
        }

        Users user = new Users();
        user.setUserName(userName);

        return usersRepository.save(user);
    }

    public Users getUserByUserName(String userName) {
        return usersRepository.findByUserName(userName);
    }
}
