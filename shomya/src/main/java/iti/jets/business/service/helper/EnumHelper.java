package iti.jets.business.service.helper;

import iti.jets.business.service.AuthService;

public class EnumHelper {
    public static AuthService.UserRole getCustomerRole() {
        return AuthService.UserRole.IS_CUSTOMER;
    }
    public static AuthService.UserRole getAdminRole() {
        return AuthService.UserRole.IS_ADMIN;
    }
}
