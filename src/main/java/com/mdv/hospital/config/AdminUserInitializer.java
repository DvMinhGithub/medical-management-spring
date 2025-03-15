package com.mdv.hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.service.AccountService;

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

    private final AccountService accountService;

    public AdminUserInitializer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        CreateAccountRequestDTO adminUser = CreateAccountRequestDTO.builder()
                .email(adminEmail)
                .password(adminPassword)
                .fullName(adminFullName)
                .build();

        accountService.createAdminUser(adminUser);
    }
}
