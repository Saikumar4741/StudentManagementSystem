package com.example.StudentManagementProject.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("students")
public class Student {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only alphabets")
    private String name;

    @Min(value = 1, message = "Age must be greater than zero")
    @Max(value = 100, message = "Age seems invalid")
    private int age;

    @NotBlank(message = "Branch cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Branch must contain only alphabets")
    private String branch;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit Indian mobile number")
    private String phoneNumber;

    @Min(value = 1, message = "Year must be between 1 and 4")
    @Max(value = 4, message = "Year must be between 1 and 4")
    private int yearOfStudy;

    @DecimalMin(value = "0.0", message = "CGPA cannot be negative")
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10")
    private double cgpa;

    private LocalDateTime createdAt;

    // Called automatically before saving
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructors
    public Student() {}

    public Student(String id, String name, int age, String branch,
                   String email, String phoneNumber, int yearOfStudy, double cgpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.branch = branch;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.yearOfStudy = yearOfStudy;
        this.cgpa = cgpa;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}