package com.neto.bemcontrolado.service;

import com.neto.bemcontrolado.model.Branch;
import com.neto.bemcontrolado.model.Inventory;
import com.neto.bemcontrolado.model.Product;
import com.neto.bemcontrolado.repository.BranchRepository;
import com.neto.bemcontrolado.repository.InventoryRepository;
import com.neto.bemcontrolado.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SpringBootApplication
@EntityScan(basePackages = "com.neto.bemcontrolado.model")
@RestController
@RequestMapping("api/v1/inventory")
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;




    public static class NewInventoryRequest{
        private final Integer branchId;
        private final List<Integer> productIds;

        public NewInventoryRequest(Integer branchId,List<Integer> productIds){
            this.branchId = branchId;
            this.productIds = productIds;
        }

        public Integer getBranchId(){
            return branchId;
        }

        public List<Integer> getProductIds() {
            return productIds;
        }

    }
    public static void main (String[] args){
        SpringApplication.run(InventoryService.class, args);
    }
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, BranchRepository branchRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @PostMapping
    public void addInventory(@RequestBody NewInventoryRequest request) {
        Integer branchId = request.getBranchId();
        List<Integer> productIds = request.getProductIds();

        // Obter a filial pelo ID
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        // Obter os produtos pelos IDs
        List<Product> productId = productRepository.findAllById(productIds);

        Inventory inventory = new Inventory();
        inventory.setBranch(branch);
        inventory.setProducts(productId);

        inventoryRepository.save(inventory);
    }

    public void createInventoryForBranch(Branch branch) {
        Inventory inventory = new Inventory();

        inventory.setBranch(branch);

        inventoryRepository.save(inventory);
    }
}
