package com.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.models.Student;
import com.api.repositories.StudentRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UserRepository userDao;
	
 @Autowired
 private StudentRepository studentDao;
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
	
//	@Autowired
//	private UserService userService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = new User();
		Student student = new Student();
		 student = studentDao.findByMobileNumber(username);
		if (student == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(student.getMobileNumber(),"",
				new ArrayList<>());
	}

//	public User createUser(User user1) {
//		user1.setPassword(bcryptEncoder.encode(user1.getPassword()));
//		return userService.createUser(user1);
//	}

	}
