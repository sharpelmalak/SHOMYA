package iti.jets.service.helper;

import iti.jets.service.AuthService;

public class EnumHelper {
    public static AuthService.UserRole getCustomerRole() {
        return AuthService.UserRole.IS_CUSTOMER;
    }
    public static AuthService.UserRole getAdminRole() {
        return AuthService.UserRole.IS_ADMIN;
    }
}
