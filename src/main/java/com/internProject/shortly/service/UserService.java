package com.internProject.shortly.service;

import com.internProject.shortly.DAO.AuthorityRepository;
import com.internProject.shortly.DAO.UserRepository;
import com.internProject.shortly.entity.Authority;
import com.internProject.shortly.entity.SecurityUser;
import com.internProject.shortly.entity.User;
import com.internProject.shortly.util.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Service
//public class UserService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        List<User> user = userRepository.findByEmail(email);
//        if (user.size() == 0) {
//            throw new UsernameNotFoundException("User details not found for the user : " + email);
//        }
//        return new SecurityUser(user.get(0));
//    }
//
//
//
//}
