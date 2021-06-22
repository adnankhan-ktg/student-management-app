package com.api.repositories;

import com.api.models.Student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepository extends MongoRepository<Student , String> {
    
       public Student findByMobileNumber(String str);       
}
