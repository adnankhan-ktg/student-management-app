package com.student_app.repositories.admin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student_app.models.admin.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {
	  public Admin findByUsername(String username);
	  public Admin findByUsernameAndPassword(String username, String password);

}
