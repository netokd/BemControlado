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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/excluir-produto/{productId}")
    public String deleteProduct(@PathVariable Integer productId,@RequestParam Integer inventoryId, RestTemplate restTemplate ){
        try{
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    "http://localhost:8080/api/v1/product/" + productId,
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                return "redirect:/inventario/" + inventoryId;
            }
            else {
                // Trate o erro de exclusão de alguma forma, por exemplo, exibindo uma mensagem de erro
                return "error"; // Ou qualquer outra página de erro que você queira mostrar
            }
        }catch (Exception e) {
            e.printStackTrace();
            // Trate a exceção de forma apropriada, você pode mostrar uma mensagem de erro ou fazer algo mais
            return "error"; // Ou qualquer outra página de erro que você queira mostrar
        }

    }



}
