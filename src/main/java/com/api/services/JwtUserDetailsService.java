package com.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.models.Student;
import com.api.models.admin.Admin;
import com.api.repositories.StudentRepository;
import com.api.repositories.admin.AdminRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
 @Autowired
 private StudentRepository studentDao;
 @Autowired
 private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
		 if(username.startsWith("91"))
		 {
		Student student = new Student();
		 student = studentDao.findByMobileNumber(username);
		if (student == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(student.getMobileNumber(),"",
				new ArrayList<>());
	}
    else {
             Admin admin = new Admin();
             
             admin = this.adminRepository.findByUsername(username);
             if(admin == null)
             {
            	 throw new UsernameNotFoundException(username);
             }
             return new org.springframework.security.core.userdetails.User(admin.getUsername(),admin.getPassword(),new ArrayList<>());
	}
		 
		 
		 
		 
		 
		 
	}

	}
