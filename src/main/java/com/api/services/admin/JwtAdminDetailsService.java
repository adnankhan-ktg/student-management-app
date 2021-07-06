package com.api.services.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.api.models.admin.Admin;
import com.api.repositories.admin.AdminRepository;

public class JwtAdminDetailsService implements UserDetailsService {
    
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		  Admin admin = new Admin();
		  
		  admin = this.adminRepository.findByUsername(username);
		  
		  if(admin == null)
		  {
			  throw new UsernameNotFoundException(username);
		  }
		  else {
//			  return new User(admin.getUsername(),admin.getPassword(),new ArrayList<>());
			  return User.builder().username(admin.getUsername()).password(admin.getPassword()).roles(admin.getRole()).build();
		  }
	}

}
