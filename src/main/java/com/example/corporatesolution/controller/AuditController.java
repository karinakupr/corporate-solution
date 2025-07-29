package com.example.corporatesolution.controller;

import com.example.corporatesolution.model.ProductAudit;
import com.example.corporatesolution.service.ProductAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {
    @Autowired
    ProductAuditService auditService;

    @GetMapping()
    public List<ProductAudit> getUser(){
        return auditService.getAll();
    }
}
