package com.sopan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sopan.api.model.Role;
import com.sopan.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
