package com.bhel.internship.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CertificateController {

    @GetMapping("/certificate")
    public String certificate(
            HttpSession session,
            Model model) {

        String studentName =
                (String) session.getAttribute("studentName");

        model.addAttribute("studentName", studentName);

        return "certificate";
    }
}