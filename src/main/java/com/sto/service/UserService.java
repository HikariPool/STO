package com.sto.service;

import com.sto.model.entity.business.User;

public interface UserService {
    boolean existsWith(String email, String password);

    void create(User user);
}
