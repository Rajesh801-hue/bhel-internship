package com.bhel.internship.repository;

import com.bhel.internship.model.Application;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Application application) {

        String sql = """
                INSERT INTO applications
                (student_email,name,phone,year,course,duration,status,
                institute_name,roll_no,branch,dob,gender,
                parent_name,address,aadhaar_no)
                VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                """;

        jdbcTemplate.update(
                sql,
                application.getStudentEmail(),
                application.getName(),
                application.getPhone(),
                application.getYear(),
                application.getCourse(),
                application.getDuration(),
                "PENDING",

                application.getInstituteName(),
                application.getRollNo(),
                application.getBranch(),
                application.getDob(),
                application.getGender(),
                application.getParentName(),
                application.getAddress(),
                application.getAadhaarNo()
        );
    }

    public List<Application> findAll() {

        String sql = "SELECT * FROM applications";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Application app = new Application();

            app.setId(rs.getInt("id"));
            app.setStudentEmail(rs.getString("student_email"));
            app.setName(rs.getString("name"));
            app.setPhone(rs.getString("phone"));
            app.setYear(rs.getString("year"));
            app.setCourse(rs.getString("course"));
            app.setDuration(rs.getString("duration"));
            app.setStatus(rs.getString("status"));

            app.setProgress(rs.getInt("progress"));
            app.setAssessmentScore(rs.getInt("assessment_score"));
            app.setAdminRemark(rs.getString("admin_remark"));

            app.setInstituteName(rs.getString("institute_name"));
            app.setRollNo(rs.getString("roll_no"));
            app.setBranch(rs.getString("branch"));
            app.setDob(rs.getString("dob"));
            app.setGender(rs.getString("gender"));
            app.setParentName(rs.getString("parent_name"));
            app.setAddress(rs.getString("address"));
            app.setAadhaarNo(rs.getString("aadhaar_no"));

            return app;
        });
    }

    public void approveApplication(int id) {
        jdbcTemplate.update(
                "UPDATE applications SET status='APPROVED' WHERE id=?",
                id
        );
    }

    public void rejectApplication(int id) {
        jdbcTemplate.update(
                "UPDATE applications SET status='REJECTED' WHERE id=?",
                id
        );
    }

    public Application findByEmail(String email) {

        String sql =
                "SELECT * FROM applications WHERE student_email=?";

        try {

            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {

                        Application app = new Application();

                        app.setId(rs.getInt("id"));
                        app.setStudentEmail(rs.getString("student_email"));
                        app.setName(rs.getString("name"));
                        app.setPhone(rs.getString("phone"));
                        app.setYear(rs.getString("year"));
                        app.setCourse(rs.getString("course"));
                        app.setDuration(rs.getString("duration"));
                        app.setStatus(rs.getString("status"));

                        app.setProgress(rs.getInt("progress"));
                        app.setAssessmentScore(rs.getInt("assessment_score"));
                        app.setAdminRemark(rs.getString("admin_remark"));

                        app.setInstituteName(rs.getString("institute_name"));
                        app.setRollNo(rs.getString("roll_no"));
                        app.setBranch(rs.getString("branch"));
                        app.setDob(rs.getString("dob"));
                        app.setGender(rs.getString("gender"));
                        app.setParentName(rs.getString("parent_name"));
                        app.setAddress(rs.getString("address"));
                        app.setAadhaarNo(rs.getString("aadhaar_no"));

                        return app;
                    },
                    email
            );

        } catch (Exception e) {
            return null;
        }
    }

    public void markCompleted(String email) {

        jdbcTemplate.update(
                "UPDATE applications SET internship_status='COMPLETED' WHERE student_email=?",
                email
        );
    }

    public String getInternshipStatus(String email) {

        try {
            return jdbcTemplate.queryForObject(
                    "SELECT internship_status FROM applications WHERE student_email=?",
                    String.class,
                    email
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void updateProgress(String email, int progress) {

        jdbcTemplate.update(
                "UPDATE applications SET progress=? WHERE student_email=?",
                progress,
                email
        );
    }

    public int getProgress(String email) {

        try {

            Integer progress = jdbcTemplate.queryForObject(
                    "SELECT progress FROM applications WHERE student_email=?",
                    Integer.class,
                    email
            );

            return progress == null ? 0 : progress;

        } catch (Exception e) {
            return 0;
        }
    }

    public void updateAssessmentScore(String email, int score) {

        jdbcTemplate.update(
                "UPDATE applications SET assessment_score=? WHERE student_email=?",
                score,
                email
        );
    }

    public void updateAdminRemark(int id, String remark) {

        jdbcTemplate.update(
                "UPDATE applications SET admin_remark=? WHERE id=?",
                remark,
                id
        );
    }

    public String getAdminRemark(String email) {

        try {

            return jdbcTemplate.queryForObject(
                    "SELECT admin_remark FROM applications WHERE student_email=?",
                    String.class,
                    email
            );

        } catch (Exception e) {
            return "";
        }
    }
}