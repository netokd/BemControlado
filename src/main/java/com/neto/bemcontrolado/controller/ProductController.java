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
        // Recupere o ID do inventário a partir do formulário
        Integer inventoryId = productForm.getInventoryId();

        // Obtenha o inventário com base no ID (você precisará injetar o repositório do inventário)
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        // Crie uma nova instância de produto e associe ao inventário
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setLabelCode(productForm.getLabelCode());
        product.setCategory(categoryRepository.findById(productForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
        product.setInventory(inventory);

        // Salve o produto no banco de dados
        productRepository.save(product);

        return "redirect:/inventario/" + inventoryId; // Redirecionar para a página de sucesso
    }
}
