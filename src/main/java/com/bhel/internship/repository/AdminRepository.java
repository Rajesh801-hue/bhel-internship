package com.bhel.internship.repository;

import com.bhel.internship.model.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Admin findByUsernameAndPassword(String username, String password) {

        String sql =
                "SELECT * FROM admin WHERE username=? AND password=?";

        try {

            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {

                        Admin admin = new Admin();

                        admin.setId(rs.getInt("id"));
                        admin.setUsername(rs.getString("username"));
                        admin.setPassword(rs.getString("password"));

                        return admin;
                    },
                    username,
                    password
            );

        } catch (Exception e) {
            return null;
        }
    }
}