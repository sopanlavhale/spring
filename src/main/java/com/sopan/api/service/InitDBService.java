package com.sopan.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopan.api.model.Blog;
import com.sopan.api.model.Item;
import com.sopan.api.model.Role;
import com.sopan.api.model.User;
import com.sopan.api.repository.BlogRepository;
import com.sopan.api.repository.ItemRepository;
import com.sopan.api.repository.RoleRepository;
import com.sopan.api.repository.UserRepository;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	@PostConstruct
	public void init() {
		
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
		
		Role adminRole = new Role();
		adminRole.setName("Role_Admin");
		roleRepository.save(adminRole);
		
		User userAdmin = new User();
		userAdmin.setName("Admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(adminRole);
		roles.add(userRole);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blog = new Blog();
		blog.setName("springData");
		blog.setUrl("https://www.flipkart.com/");
		blog.setUser(userAdmin);
		blogRepository.save(blog);
		
		Item item = new Item();
		item.setBlog(blog);
		item.setTitle("first");
		item.setLink("https://www.flipkart.com/");
		item.setPublishedDate(new Date());
		itemRepository.save(item);
		
		Item item1 = new Item();
		item1.setBlog(blog);
		item1.setTitle("second");
		item1.setLink("https://www.flipkart.com/");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
	}
	
	
}
