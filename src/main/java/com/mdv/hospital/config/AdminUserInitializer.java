package com.mdv.hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mdv.hospital.dto.request.CreateUserDTO;
import com.mdv.hospital.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminUserInitializer implements CommandLineRunner {

    @Value("${app.account.admin.email}")
    private String adminEmail;

    @Value("${app.account.admin.password}")
    private String adminPassword;

    @Value("${app.account.admin.fullName}")
    private String adminFullName;

    private final UserService userService;

    public AdminUserInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        CreateUserDTO adminUser = CreateUserDTO.builder()
                .email(adminEmail)
                .password(adminPassword)
                .fullName(adminFullName)
                .build();

        userService.createAdminUser(adminUser);
    }
}
