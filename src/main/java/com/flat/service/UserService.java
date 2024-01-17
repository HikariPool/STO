package com.flat.service;

import com.flat.model.entity.business.User;

public interface UserService {
    boolean existsWith(String email, String password);

    void create(User user);
}
