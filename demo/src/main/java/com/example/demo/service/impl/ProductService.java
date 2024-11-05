package com.example.demo.service.impl;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.document.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private static final Logger logger =  LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        logger.error(product.getName());
        return productRepository.save(product);
    }

    public Product getById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void delete(String productId) {
        productRepository.deleteById(productId);
    }
}
