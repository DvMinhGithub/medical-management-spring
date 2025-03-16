package com.mdv.hospital.config;

public class Constants {
    public static final String[] PUBLIC_ENDPOINTS = {
        "/accounts/register",
        "/accounts/login",
        "/accounts/check-user/**",
        "/accounts/active/**",
        "/accounts/reset-password/**",
        "/error/**"
    };

    public static final String[] ADMIN_GET_ENDPOINTS = {"/accounts", "/accounts/patient", "/accounts/doctor"};

    public static final String[] ADMIN_POST_ENDPOINTS = {"/facilities", "/services", "/medicines"};

    public static final String[] ADMIN_DELETE_ENDPOINTS = {"/medicines/**"};

    public static final String[] ADMIN_DOCTOR_GET_ENDPOINTS = {"/accounts/patient-done-orders"};
}
