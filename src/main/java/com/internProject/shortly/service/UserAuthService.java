package com.internProject.shortly.service;

import com.internProject.shortly.DAO.AuthorityRepository;
import com.internProject.shortly.DAO.UserRepository;
import com.internProject.shortly.entity.Authority;
import com.internProject.shortly.entity.User;
import com.internProject.shortly.util.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    @Transactional
    public boolean checkUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.existsByEmail(email);
    }


    @Transactional
    public User createUser(String email, String pwd){
        //create new user
        User user = new User();

        //set email and encoded password
        user.setEmail(email);
        user.setPwd(passwordEncoder.encode(pwd));
        user.setRole("user");

        //set Authority
        Set<Authority> authorities = new HashSet<>();
        Authority userAuthority = new Authority();
        userAuthority.setName("ROLE_USER");
        userAuthority.setUser(user);

        authorities.add(userAuthority);
        user.setAuthorities(authorities);

        //save user and authority
        authorityRepository.save(userAuthority);
        userRepository.save(user);
        return user;
    }


    @Transactional
    public String logInUser(String email, String pwd) {

        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JWTGenerator.generateToken(authentication);


        return jwt;
    }
}
