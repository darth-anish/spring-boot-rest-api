package com.fhkiel.product.controller;

import com.fhkiel.product.entity.Product;
import com.fhkiel.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    // Create a new product
    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        return productService.saveProduct(product);
//        return newProduct;
    }

    // Get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return productService.fetchAllProducts();
    }
    // Get a product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>>getProductById(@PathVariable Integer id)
    {
        return productService.fetchProductById(id);

    }
    //    update a product by ID
    // delete a product by ID
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Integer id, @RequestBody Product updatedProduct){
        return productService.updateProduct(id, updatedProduct);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id)
    {
        return productService.deleteProduct(id);

    }
}
