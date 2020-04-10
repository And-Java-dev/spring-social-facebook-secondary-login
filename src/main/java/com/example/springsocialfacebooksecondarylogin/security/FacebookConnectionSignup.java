package com.example.springsocialfacebooksecondarylogin.security;

import com.example.springsocialfacebooksecondarylogin.persistence.model.User;
import com.example.springsocialfacebooksecondarylogin.persistence.repository.UserReopository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;


@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserReopository userReopository;


    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
        final User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(System.currentTimeMillis() + "555");
        userReopository.save(user);
        return user.getUsername();
    }
}
