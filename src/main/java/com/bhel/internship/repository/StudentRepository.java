package com.bhel.internship.repository;

import com.bhel.internship.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student student) {
        String sql = "INSERT INTO students(name,email,password) VALUES(?,?,?)";

        jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getPassword()
        );
    }

    public Student findByEmailAndPassword(String email, String password) {

        String sql = "SELECT * FROM students WHERE email=? AND password=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setName(rs.getString("name"));
                        student.setEmail(rs.getString("email"));
                        student.setPassword(rs.getString("password"));
                        return student;
                    },
                    email,
                    password
            );
        } catch (Exception e) {
            return null;
        }
    }
}