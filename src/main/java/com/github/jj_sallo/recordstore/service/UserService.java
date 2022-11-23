package com.github.jj_sallo.recordstore.service;

import com.github.javafaker.Faker;
import com.github.jj_sallo.recordstore.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static List<User> generateUsers(int n) {
        List<User> userList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < n; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            userList.add(user);
        }
        return userList;
    }
}
