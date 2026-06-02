package com.bhel.internship.service;

import com.bhel.internship.model.Admin;
import com.bhel.internship.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin login(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}