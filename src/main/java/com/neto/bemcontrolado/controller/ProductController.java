package com.neto.bemcontrolado.controller;

import com.neto.bemcontrolado.model.Branch;
import com.neto.bemcontrolado.model.Category;
import com.neto.bemcontrolado.model.Inventory;
import com.neto.bemcontrolado.model.Product;
import com.neto.bemcontrolado.modelos.AddProductForm;
import com.neto.bemcontrolado.repository.BranchRepository;
import com.neto.bemcontrolado.repository.CategoryRepository;
import com.neto.bemcontrolado.repository.InventoryRepository;
import com.neto.bemcontrolado.repository.ProductRepository;
import com.neto.bemcontrolado.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepository; // Repositório de Categorias
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryRepository inventoryRepository; // Repositório de Inventários
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BranchRepository branchRepository;
    @GetMapping("/produto")
    public String branch(){
        return "home";
    }

    @GetMapping("/adicionar-produto")
    public String addProduct(@RequestParam(name = "branchId") Integer branchId, Model model){
        List<Category> categories = categoryRepository.findAll();
        List<Inventory> inventories = inventoryRepository.findAll();
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));

        model.addAttribute("categories", categories);
        model.addAttribute("branch", branch);
        model.addAttribute("inventories", inventories);
        model.addAttribute("productForm", new AddProductForm(null, null, null, null, branchId));


        return "product/create-product";
    }
    @PostMapping("/adicionar-produto")
    public String addProduct(@ModelAttribute AddProductForm productForm) {
        // Use o ProductService para adicionar o produto
        productService.addProduct(productForm);

        // Redirecione para a página de sucesso
        return "redirect:/inventario/" + productForm.getInventoryId();
    }



}
