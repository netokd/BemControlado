package com.neto.bemcontrolado.controller;

import com.neto.bemcontrolado.model.Branch;
import com.neto.bemcontrolado.model.Inventory;
import com.neto.bemcontrolado.repository.BranchRepository;
import com.neto.bemcontrolado.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class InventoryContoller {

    private final BranchRepository branchRepository;
    private final InventoryRepository inventoryRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public InventoryContoller(BranchRepository branchRepository, InventoryRepository inventoryRepository, RestTemplate restTemplate) {
        this.branchRepository = branchRepository;
        this.inventoryRepository = inventoryRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/inventario")
    public String inventory(Model model){
        List<Inventory> inventory = inventoryRepository.findAll();
        model.addAttribute("inventoryList", inventory);
        return "inventory/inventory";
    }

    @GetMapping("/adicionar-inventario")
    public String showAddInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "inventory/create-inventory";
    }
    @PostMapping("/adicionar-inventario")
    public String addInventory(@ModelAttribute Inventory inventory) {
        // Defina a filial do inventário (branch) com base nos dados do formulário
        Branch branch = branchRepository.findById(inventory.getBranch().getId())
                .orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        inventory.setBranch(branch);

        // Salve o inventário no banco de dados
        inventoryRepository.save(inventory);

        return "redirect:/inventario";
    }
}

