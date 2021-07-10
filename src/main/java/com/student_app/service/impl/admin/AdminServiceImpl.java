package com.student_app.service.impl.admin;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.student_app.models.admin.Admin;
import com.student_app.repositories.admin.AdminRepository;
import com.student_app.services.admin.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
   
	@Autowired
	private AdminRepository AdminRepository;
	 @Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public Admin addAdmin(Admin admin) {
		admin.setId(UUID.randomUUID().toString());
		
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
	@Override
	public Admin updateAdmin(Admin admin) {
		Admin tempAdmin = null;
		try {
		             tempAdmin =this.AdminRepository.save(admin);
		             return tempAdmin;
		}
		catch (Exception e) {
			e.printStackTrace();
			return tempAdmin;
		}
	}

}
