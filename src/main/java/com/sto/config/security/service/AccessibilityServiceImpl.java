package com.sto.config.security.service;

import com.sto.data.constants.Constants;
import com.sto.model.entity.business.User;
import com.sto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccessibilityServiceImpl {
    @Autowired
    private UserService userService;


    public boolean currentUserHasAccess() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return currentUser.getRole() != null && Constants.ADMIN.equals(currentUser.getRole());
    }
}
