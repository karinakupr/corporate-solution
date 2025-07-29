package com.example.corporatesolution.service;

import com.example.corporatesolution.model.Product;
import com.example.corporatesolution.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setQuantity(updatedProduct.getQuantity());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}