package com.example.StudentManagementProject;

import com.example.StudentManagementProject.Repository.StudentRepo;
import com.example.StudentManagementProject.ServiceLayer.StudentService;
import com.example.StudentManagementProject.exception.DuplicateIdException;
import com.example.StudentManagementProject.exception.StudentNotFoundException;
import com.example.StudentManagementProject.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepo repo;

    @InjectMocks
    StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("S001", "Ravi Kumar", 20, "CSE",
                "ravi@gmail.com", "9876543210", 2, 8.5);
    }

    // ✅ Test 1 - getStudents() returns all students
    @Test
    void testGetStudents_ReturnsAllStudents() {
        when(repo.findAll()).thenReturn(List.of(student));

        List<Student> result = service.getStudents();

        assertEquals(1, result.size());
        assertEquals("Ravi Kumar", result.get(0).getName());
        verify(repo, times(1)).findAll();
    }

    // ✅ Test 2 - getStudentById() returns student when found
    @Test
    void testGetStudentById_WhenFound_ReturnsStudent() {
        when(repo.findById("S001")).thenReturn(Optional.of(student));

        Optional<Student> result = service.getStudentById("S001");

        assertTrue(result.isPresent());
        assertEquals("Ravi Kumar", result.get().getName());
    }

    // ✅ Test 3 - getStudentById() returns empty when not found
    @Test
    void testGetStudentById_WhenNotFound_ReturnsEmpty() {
        when(repo.findById("S999")).thenReturn(Optional.empty());

        Optional<Student> result = service.getStudentById("S999");

        assertFalse(result.isPresent());
    }

    // ✅ Test 4 - addStudent() saves successfully
    @Test
    void testAddStudent_Success() {
        when(repo.existsById("S001")).thenReturn(false);
        when(repo.save(student)).thenReturn(student);

        Student result = service.addStudent(student);

        assertNotNull(result);
        assertEquals("S001", result.getId());
        verify(repo, times(1)).save(student);
    }

    // ✅ Test 5 - addStudent() throws DuplicateIdException
    @Test
    void testAddStudent_DuplicateId_ThrowsException() {
        when(repo.existsById("S001")).thenReturn(true);

        assertThrows(DuplicateIdException.class, () -> service.addStudent(student));
        verify(repo, never()).save(any()); // save should never be called
    }

    // ✅ Test 6 - updateStudent() updates successfully
    @Test
    void testUpdateStudent_Success() {
        Student updatedDetails = new Student("S001", "Ravi Updated", 21, "IT",
                "ravi.new@gmail.com", "9876543210", 3, 9.0);

        when(repo.findById("S001")).thenReturn(Optional.of(student));
        when(repo.save(any(Student.class))).thenReturn(updatedDetails);

        Student result = service.updateStudent("S001", updatedDetails);

        assertEquals("Ravi Updated", result.getName());
        assertEquals("IT", result.getBranch());
        verify(repo, times(1)).save(any(Student.class));
    }

    // ✅ Test 7 - updateStudent() throws exception when not found
    @Test
    void testUpdateStudent_NotFound_ThrowsException() {
        when(repo.findById("S999")).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class,
                () -> service.updateStudent("S999", student));
    }

    // ✅ Test 8 - deleteStudent() deletes successfully
    @Test
    void testDeleteStudent_Success() {
        when(repo.existsById("S001")).thenReturn(true);
        doNothing().when(repo).deleteById("S001");

        boolean result = service.deleteStudent("S001");

        assertTrue(result);
        verify(repo, times(1)).deleteById("S001");
    }

    // ✅ Test 9 - deleteStudent() throws exception when not found
    @Test
    void testDeleteStudent_NotFound_ThrowsException() {
        when(repo.existsById("S999")).thenReturn(false);

        assertThrows(StudentNotFoundException.class,
                () -> service.deleteStudent("S999"));
        verify(repo, never()).deleteById(any());
    }
}