# Student Management System

A RESTful API built with Spring Boot and MongoDB for managing student records.

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3.5.7
- MongoDB
- Maven

## ✨ Features
- CRUD operations for students
- Bean Validation
- Custom Exception Handling
- Unit Tests with JUnit 5 & Mockito

## 📦 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /GET/students | Get all students |
| GET | /GET/students/{id} | Get student by ID |
| POST | /POST/students | Add new student |
| PUT | /PUT/students/{id} | Update student |
| DELETE | /DELETE/students/{id} | Delete student |

## 🚀 Run Locally
1. Clone the repo
```
   git clone https://github.com/Saikumar4741/StudentManagementSystem.git
```
2. Make sure MongoDB is running locally
3. Run the app
```
   mvn spring-boot:run
```
4. API is available at `http://localhost:8080`

## 👨‍💻 Author
K Sai Kumar
