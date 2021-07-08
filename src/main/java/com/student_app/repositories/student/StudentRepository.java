package com.student_app.repositories.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student_app.models.student.Student;



@Repository
public interface StudentRepository extends MongoRepository<Student , String> {
    
       public Student findByMobileNumber(String str);
       
}
