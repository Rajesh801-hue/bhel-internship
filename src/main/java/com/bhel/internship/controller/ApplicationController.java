package com.bhel.internship.controller;

import com.bhel.internship.model.Application;
import com.bhel.internship.service.ApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/apply")
    public String applicationForm() {
        return "application-form";
    }

    @PostMapping("/apply")
    public String submitApplication(
            @ModelAttribute Application application,
            Model model) {

        Application existing =
                applicationService.getApplicationByEmail(
                        application.getStudentEmail()
                );

        if (existing != null) {

            model.addAttribute(
                    "error",
                    "You have already submitted an internship application."
            );

            return "application-form";
        }

        applicationService.submitApplication(application);

        return "application-success";
    }

    @GetMapping("/complete-module/{progress}")
    public String completeModule(
            @PathVariable int progress,
            HttpSession session) {

        String email =
                (String) session.getAttribute("studentEmail");

        applicationService.updateProgress(email, progress);

        return "redirect:/course";
    }
}