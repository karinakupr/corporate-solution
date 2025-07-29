package com.example.corporatesolution.service;

import com.example.corporatesolution.model.Product;
import com.example.corporatesolution.model.ProductAudit;
import com.example.corporatesolution.repository.ProductAuditRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductAuditService {

    private final ProductAuditRepository productAuditRepository;

    public ProductAuditService(ProductAuditRepository productAuditRepository) {
        this.productAuditRepository = productAuditRepository;
    }

    public ProductAudit productChange(Product product, String operationType, String modifiedBy) {
        ProductAudit audit = new ProductAudit();
        audit.setProduct(product.getName());
        audit.setOperationType(operationType);
        audit.setModifiedBy(modifiedBy);
        audit.setModifiedAt(LocalDateTime.now());

        return productAuditRepository.save(audit);
    }


    public List<ProductAudit> getAll(){
        return productAuditRepository.findAll();
    }
}
