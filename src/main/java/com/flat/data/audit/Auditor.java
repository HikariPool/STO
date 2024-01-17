package com.flat.data.audit;

import com.flat.model.entity.business.User;
import com.flat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Auditor implements AuditorAware<User> {

    @Autowired
    private UserRepo userRepo;


    @Override
    public Optional<User> getCurrentAuditor() {
        return userRepo.findById(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}