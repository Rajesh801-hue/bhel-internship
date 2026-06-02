package com.bhel.internship.service;

import com.bhel.internship.model.Application;
import com.bhel.internship.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void submitApplication(Application application) {
        applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public void approveApplication(int id) {
        applicationRepository.approveApplication(id);
    }

    public void rejectApplication(int id) {
        applicationRepository.rejectApplication(id);
    }

    public Application getApplicationByEmail(String email) {
        return applicationRepository.findByEmail(email);
    }

    public void markCompleted(String email) {
        applicationRepository.markCompleted(email);
    }

    public String getInternshipStatus(String email) {
        return applicationRepository.getInternshipStatus(email);
    }

    public void updateProgress(String email, int progress) {
        applicationRepository.updateProgress(email, progress);
    }

    public int getProgress(String email) {
        return applicationRepository.getProgress(email);
    }

    public void updateAssessmentScore(String email, int score) {
        applicationRepository.updateAssessmentScore(email, score);
    }

    public void updateAdminRemark(int id, String remark) {
        applicationRepository.updateAdminRemark(id, remark);
    }

    public String getAdminRemark(String email) {
        return applicationRepository.getAdminRemark(email);
    }
}