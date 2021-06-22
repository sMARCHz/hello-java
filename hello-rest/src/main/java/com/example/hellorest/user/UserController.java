package com.example.hellorest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserList usersList;

    @GetMapping("/user")
    public List<UserResponse> getAllUsers() {
        return usersList.getUsersList();
    }

    @GetMapping("/user/{id}")
    public UserResponse getUserByID(@PathVariable String id) {
        int _id = 0;
        try {
            _id = Integer.parseInt(id);
        } catch (NumberFormatException err) {
            System.out.println("Invalid ID.");
        }
        return usersList.getUserByID(_id);
    }
}
