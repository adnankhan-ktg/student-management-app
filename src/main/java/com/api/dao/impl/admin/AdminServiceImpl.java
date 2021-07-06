package com.api.dao.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.models.admin.Admin;
import com.api.repositories.admin.AdminRepository;
import com.api.services.admin.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
   
	@Autowired
	private AdminRepository AdminRepository;
	 @Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public Admin addAdmin(Admin admin) {
		
//		admin.setPassword(bcryptEncoder.encode(admin.getPassword()));
		  Admin tempAdmin = null;
            try {
                   tempAdmin = this.AdminRepository.save(admin);
                   return tempAdmin;
            }catch (Exception e) {
			    e.printStackTrace();
			    return tempAdmin;
			}
	}

}
