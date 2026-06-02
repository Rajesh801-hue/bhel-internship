package com.bhel.internship.controller;

import com.bhel.internship.service.ApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssessmentController {

    private final ApplicationService applicationService;

    public AssessmentController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/submit-assessment")
    public String submitAssessment(

            @RequestParam String q1,
            @RequestParam String q2,
            @RequestParam String q3,
            @RequestParam String q4,
            @RequestParam String q5,
            @RequestParam String q6,
            @RequestParam String q7,
            @RequestParam String q8,
            @RequestParam String q9,
            @RequestParam String q10,

            HttpSession session,
            Model model) {

        int score = 0;

        if(q1.equals("a")) score++;
        if(q2.equals("b")) score++;
        if(q3.equals("b")) score++;
        if(q4.equals("c")) score++;
        if(q5.equals("a")) score++;
        if(q6.equals("a")) score++;
        if(q7.equals("c")) score++;
        if(q8.equals("a")) score++;
        if(q9.equals("a")) score++;
        if(q10.equals("b")) score++;

        String email =
                (String) session.getAttribute("studentEmail");

        applicationService.updateAssessmentScore(email, score);

        model.addAttribute("score", score);

        if(score >= 6) {

            applicationService.markCompleted(email);

            model.addAttribute("result", "PASS");

        } else {

            model.addAttribute("result", "FAIL");
        }

        return "result";
    }
}