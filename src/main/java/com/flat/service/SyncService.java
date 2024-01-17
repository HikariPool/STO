package com.flat.service;

import com.flat.model.dto.SyncResult;

public interface SyncService {
    SyncResult sync(Long sessionId, Float currentTime);

    void clear();
}
