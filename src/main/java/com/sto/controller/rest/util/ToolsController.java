package com.sto.controller.rest.util;

import com.sto.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    private SyncService syncService;


    @GetMapping("/clearSync")
    public void clearSync() {
        syncService.clear();
    }
}
