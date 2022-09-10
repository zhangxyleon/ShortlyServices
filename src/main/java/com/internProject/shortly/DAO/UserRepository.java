package com.internProject.shortly.DAO;

import com.internProject.shortly.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
