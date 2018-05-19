package com.sopan.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopan.api.model.Blog;
import com.sopan.api.model.Item;
import com.sopan.api.model.User;
import com.sopan.api.repository.BlogRepository;
import com.sopan.api.repository.ItemRepository;
import com.sopan.api.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public List<User> findAll() {

		return userRepository.findAll();
	}

	public User findById(int userId) {

		return userRepository.findOne(userId);
	}

	
	public User findOneWithBlogs(int userId) {
		User user = findById(userId);
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog);
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		userRepository.save(user);
		
	}
}
