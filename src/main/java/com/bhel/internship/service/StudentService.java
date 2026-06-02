package com.bhel.internship.service;

import com.bhel.internship.model.Student;
import com.bhel.internship.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void registerStudent(Student student) {
        studentRepository.save(student);
    }

    public Student login(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }
}