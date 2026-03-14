package com.example.StudentManagementProject.Repository;

import com.example.StudentManagementProject.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;


// MongoRepository already supports Pageable out of the box
// Just add this one method for branch search:
public interface StudentRepo extends MongoRepository<Student, String> {

}
