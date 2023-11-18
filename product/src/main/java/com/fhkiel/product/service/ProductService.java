package com.fhkiel.product.service;

import com.fhkiel.product.entity.Product;
import com.fhkiel.product.repository.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        System.out.println("good until here");
        Product newProduct = productRepository.save(product);
        System.out.println(newProduct);
        return ResponseEntity.ok(newProduct);
    }
    // Get all products
    public ResponseEntity<List<Product>> fetchAllProducts()
    {
        return ResponseEntity.ok(productRepository.findAll());
    }
    // Get a product by ID
    public ResponseEntity<Optional<Product>> fetchProductById(Integer id)
    {
        Optional<Product> product
                = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    // update
    public ResponseEntity<Product> updateProduct(Integer id, Product updatedProduct)
    {
        if (id == null) {
            throw new IllegalArgumentException(
                    "ID cannot be null");
        }
        Product Existingproduct
                = productRepository.findById(id).orElseThrow(
                ()
                        -> new EntityNotFoundException(
                        String.valueOf(id)));
        Existingproduct.setName(updatedProduct.getName());
        Existingproduct.setPrice(updatedProduct.getPrice());

        Product savedEntity
                = productRepository.save(Existingproduct);
        return ResponseEntity.ok(savedEntity);
    }
    // delete
    public ResponseEntity<String> deleteProduct(Integer id)
    {
        productRepository.deleteById(id);
        return ResponseEntity.ok(
            "Product Deleted Successfully");
    }

}
