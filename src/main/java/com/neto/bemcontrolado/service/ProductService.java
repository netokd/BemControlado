package com.neto.bemcontrolado.service;


import com.neto.bemcontrolado.model.Category;
import com.neto.bemcontrolado.model.Inventory;
import com.neto.bemcontrolado.model.Product;
import com.neto.bemcontrolado.repository.CategoryRepository;
import com.neto.bemcontrolado.repository.InventoryRepository;
import com.neto.bemcontrolado.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping("api/v1/product")
public class ProductService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository    categoryRepository;

    public ProductService(InventoryRepository inventoryRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main (String[] args){
        SpringApplication.run(ProductService.class, args);
    }

    @GetMapping
    public List<Product> getProduct(){
        return productRepository.findAll();
    }


    record NewProductRequest(
            String name,
            String description,
            String labelCode,
            Integer categoryId,
            Integer inventoryId
    ){}

    @PostMapping
    public void addProduct(@RequestBody NewProductRequest request){
        Integer inventoryId = request.inventoryId;
        Integer categoryId = request.categoryId;

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Product product = new Product();
        product.setName(request.name);
        product.setDescription(request.description);
        product.setLabelCode(request.labelCode);
        product.setCategory(category);
        product.setInventory(inventory);

        productRepository.save(product);
    }

}
