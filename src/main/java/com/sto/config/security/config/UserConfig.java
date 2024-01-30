package com.sto.config.security.config;

import com.sto.data.constants.Constants;
import com.sto.model.entity.business.User;
import com.sto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class UserConfig {
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    private UserService userService;


    @PostConstruct
    public void createSuperUser() {
        if (!userService.existsWith(adminUsername, adminPassword)) {
            User user = User.builder()
                    .username(adminUsername)
                    .password(adminPassword)
                    .email(adminEmail)
                    .role(Constants.ADMIN).build();
            userService.create(user);
        }
    }
}
