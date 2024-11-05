package com.example.demo.controller;


import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.document.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable(name = "id") String productId) {
        try {
            Product product = productService.getById(productId);
            if (product == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) {

        try {
            //return new ResponseEntity<>(productService.save(productMapper.productDTOToProduct(productDTO)), HttpStatus.OK);
            Product product = productService.save(productMapper.productDTOToProduct(productDTO));
            if (product == null)
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            else
                return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            return new ResponseEntity<>(productService.save(productMapper.productDTOToProduct(productDTO)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") String productDeleteId) {
        try {
            productService.delete(productDeleteId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

