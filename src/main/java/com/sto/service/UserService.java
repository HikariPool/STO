package com.sto.service;

import com.sto.model.entity.business.User;

public interface UserService {
    boolean existsWith(String username, String password);

    void create(User user);

    User getAdmin();
}
