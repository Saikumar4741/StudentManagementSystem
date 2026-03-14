package com.example.StudentManagementProject.ServiceLayer;

import com.example.StudentManagementProject.Repository.StudentRepo;
import com.example.StudentManagementProject.exception.DuplicateIdException;
import com.example.StudentManagementProject.exception.StudentNotFoundException;
import com.example.StudentManagementProject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo repo;

    public List<Student> getStudents() {
        return repo.findAll();
    }

    public Optional<Student> getStudentById(String studentId) {
        return repo.findById(studentId);
    }

    public Student addStudent(Student student) {
        if (repo.existsById(student.getId()))
            throw new DuplicateIdException("ID " + student.getId() + " already exists");
        student.onCreate(); // ✅ sets createdAt automatically
        return repo.save(student);
    }

    public Student updateStudent(String studentId, Student studentDetails) {
        Student existing=repo.findById(studentId)
                .orElseThrow(()-> new StudentNotFoundException("Student with ID "+studentId+"not found"));
        existing.setName(studentDetails.getName());
        existing.setAge(studentDetails.getAge());
        existing.setBranch(studentDetails.getBranch());

        return repo.save(existing);
    }


    public boolean deleteStudent(String studentId) {
        if(repo.existsById(studentId)){
            repo.deleteById(studentId);
            return true;
        }
        else throw new StudentNotFoundException("Student with ID "+studentId+"not found");
    }
}
