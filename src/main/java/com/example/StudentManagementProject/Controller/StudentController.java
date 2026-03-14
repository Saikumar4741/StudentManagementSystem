package com.example.StudentManagementProject.Controller;

import com.example.StudentManagementProject.model.Student;
import com.example.StudentManagementProject.ServiceLayer.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/GET/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = service.getStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/GET/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        return service.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/POST/students")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student saved = service.addStudent(student);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/PUT/students/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String studentId,
            @Valid @RequestBody Student student) {
        Student updated = service.updateStudent(studentId, student);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/DELETE/students/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable String studentId) {
        service.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}