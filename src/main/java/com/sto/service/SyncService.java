package com.sto.service;

import com.sto.model.dto.SyncResult;

public interface SyncService {
    SyncResult sync(Long sessionId, Float currentTime);

    void clear();
}
