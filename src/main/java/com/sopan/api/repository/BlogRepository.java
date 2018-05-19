package com.sopan.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sopan.api.model.Blog;
import com.sopan.api.model.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	List<Blog> findByUser(User user);
}
