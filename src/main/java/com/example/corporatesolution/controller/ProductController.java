package com.example.corporatesolution.controller;

import com.example.corporatesolution.model.Product;
import com.example.corporatesolution.service.ProductAuditService;
import com.example.corporatesolution.service.ProductService;
import com.example.corporatesolution.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductAuditService auditService;

    private final JwtUtil jwtUtil;

    public ProductController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping()
    public List<Product> getUser(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody Product product, @RequestHeader("Authorization") String authHeader) {

        if (!haveAdminRole(authHeader)) {
            return ResponseEntity.status(403).body("Access Denied: Administrator Only");
        }
        String username = jwtUtil.getUsernameFromToken(authHeader.substring(7));
        auditService.productChange(product, "CREATE", username);

        Product saved = productService.createProduct(product);
            return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestHeader("Authorization") String authHeader) {

        if (!haveAdminRole(authHeader)) {
            return ResponseEntity.status(403).body("Access Denied: Administrator Only");
        }
        String username = jwtUtil.getUsernameFromToken(authHeader.substring(7));
        auditService.productChange(product, "UPDATE", username);
        Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Product product, @RequestHeader("Authorization") String authHeader) {

        if (!haveAdminRole(authHeader)) {
            return ResponseEntity.status(403).body("Access Denied: Administrator Only");
        }
        String username = jwtUtil.getUsernameFromToken(authHeader.substring(7));
        auditService.productChange(product, "CREATE", username);
        productService.deleteProduct(product.getId());
        return ResponseEntity.ok("Product deleted");
    }

    private boolean haveAdminRole(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);
        String role = jwtUtil.getRoleFromToken(token);
        return "ADMIN".equals(role);
    }
}