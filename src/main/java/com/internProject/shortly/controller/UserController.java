package com.internProject.shortly.controller;


import com.internProject.shortly.entity.Authority;
import com.internProject.shortly.entity.User;
import com.internProject.shortly.service.UserAuthService;
import com.internProject.shortly.service.UserService;
import com.internProject.shortly.util.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserAuthService userService;




    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> signUpRequest){

        //check whether user exists
        if (userService.checkUserByEmail(signUpRequest.get("email"))){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = userService.createUser(signUpRequest.get("email"), signUpRequest.get("pwd"));
        return ResponseEntity.ok("User registered successfully!");

    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> logInRequest){


        String jwt = userService.logInUser(logInRequest.get("email"), logInRequest.get("pwd"));


        return ResponseEntity.ok(jwt);

    }


    @PostMapping("/oauth")
    public ResponseEntity<?>  main(OAuth2AuthenticationToken token) {
        System.out.println(token.getPrincipal());
        return ResponseEntity.ok("login successfully!");
    }



}
