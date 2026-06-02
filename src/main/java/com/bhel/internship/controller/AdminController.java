package com.bhel.internship.controller;

import com.bhel.internship.model.Admin;
import com.bhel.internship.service.AdminService;
import com.bhel.internship.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final ApplicationService applicationService;

    public AdminController(AdminService adminService,
                           ApplicationService applicationService) {
        this.adminService = adminService;
        this.applicationService = applicationService;
    }

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String adminLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Admin admin = adminService.login(username, password);

        if (admin != null) {

            model.addAttribute(
                    "applications",
                    applicationService.getAllApplications()
            );

            return "admin-dashboard";
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "admin-login";
    }

    @PostMapping("/approve/{id}")
    public String approveApplication(@PathVariable int id) {

        applicationService.approveApplication(id);

        return "redirect:/admin-dashboard";
    }

    @PostMapping("/reject/{id}")
    public String rejectApplication(@PathVariable int id) {

        applicationService.rejectApplication(id);

        return "redirect:/admin-dashboard";
    }

    @PostMapping("/remark/{id}")
    public String saveRemark(
            @PathVariable int id,
            @RequestParam String remark) {

        applicationService.updateAdminRemark(id, remark);

        return "redirect:/admin-dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model) {

        model.addAttribute(
                "applications",
                applicationService.getAllApplications()
        );

        return "admin-dashboard";
    }
}