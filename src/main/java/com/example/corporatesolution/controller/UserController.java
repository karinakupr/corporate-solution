package com.example.corporatesolution.controller;

import com.example.corporatesolution.model.User;
import com.example.corporatesolution.service.UserService;
import com.example.corporatesolution.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userInput) {
        User user = userService.getByCredentials(userInput.getName(), userInput.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).body("Not correct user");
        }

        String token = jwtUtil.generateToken(user.getName(), user.getRole());
        return ResponseEntity.ok().body("Token: " + token);
    }
}