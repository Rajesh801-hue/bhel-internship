package com.bhel.internship.controller;

import com.bhel.internship.service.ApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ApplicationService applicationService;

    public HomeController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/course")
    public String coursePage(
            HttpSession session,
            Model model) {

        String email =
                (String) session.getAttribute("studentEmail");

        if (email == null) {
            return "redirect:/login";
        }

        int progress =
                applicationService.getProgress(email);

        model.addAttribute("progress", progress);

        return "course";
    }

    @GetMapping("/assessment")
    public String assessmentPage(
            HttpSession session,
            Model model) {

        String email =
                (String) session.getAttribute("studentEmail");

        if (email == null) {
            return "redirect:/login";
        }

        int progress =
                applicationService.getProgress(email);

        if (progress < 70) {

            model.addAttribute("progress", progress);

            model.addAttribute(
                    "error",
                    "Complete at least 70% of the course before taking assessment."
            );

            return "course";
        }

        return "assessment";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}