package com.bhel.internship.controller;

import com.bhel.internship.model.Application;
import com.bhel.internship.model.Student;
import com.bhel.internship.service.ApplicationService;
import com.bhel.internship.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final ApplicationService applicationService;

    public StudentController(StudentService studentService,
                             ApplicationService applicationService) {
        this.studentService = studentService;
        this.applicationService = applicationService;
    }

    @PostMapping("/register")
    public String registerStudent(
            @ModelAttribute Student student,
            Model model) {

        try {

            studentService.registerStudent(student);

            return "redirect:/login";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "Email already registered. Please login."
            );

            return "register";
        }
    }

    @PostMapping("/login")
    public String loginStudent(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        Student student = studentService.login(email, password);

        if (student != null) {

            session.setAttribute("studentName", student.getName());
            session.setAttribute("studentEmail", student.getEmail());

            model.addAttribute("studentName", student.getName());

            Application application =
                    applicationService.getApplicationByEmail(email);

            if (application != null) {

                String internshipStatus =
                        applicationService.getInternshipStatus(email);

                int progress =
                        applicationService.getProgress(email);

                String adminRemark =
                        applicationService.getAdminRemark(email);

                model.addAttribute("status", application.getStatus());
                model.addAttribute("internshipStatus", internshipStatus);
                model.addAttribute("progress", progress);
                model.addAttribute("adminRemark", adminRemark);

            } else {

                model.addAttribute("status", "NOT_APPLIED");
                model.addAttribute("progress", 0);
            }

            return "student-dashboard";
        }

        model.addAttribute("error", "Invalid Email or Password");
        return "login";
    }


}