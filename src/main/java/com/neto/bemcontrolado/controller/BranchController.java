package com.neto.bemcontrolado.controller;

import com.neto.bemcontrolado.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.neto.bemcontrolado.repository.BranchRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class BranchController {
    private final BranchRepository branchRepository;
    private final RestTemplate restTemplate ;

    @Autowired
    public BranchController(BranchRepository branchRepository, RestTemplate restTemplate) {
        this.branchRepository = branchRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/filial")
    public String branch(Model model){
        List<Branch> branch = branchRepository.findAll();
        model.addAttribute("branchList", branch);
        return "branch/branch";
    }

    @GetMapping("/adicionar-filial")
    public String showAddCustomerForm(Model model
    ) {
        model.addAttribute("branch", new Branch());
        return "branch/create-branch";
    }

    @GetMapping("/excluir-branch/{branchId}")
    public String deleteBranch(@PathVariable Integer branchId, RestTemplate restTemplate){
        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    "http://localhost:8080/api/v1/branch/" + branchId,
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );


            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return "redirect:/filias"; // Redireciona para a página de listagem após a exclusão
            } else {
                // Trate o erro de exclusão de alguma forma, por exemplo, exibindo uma mensagem de erro
                return "error"; // Ou qualquer outra página de erro que você queira mostrar
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Trate a exceção de forma apropriada, você pode mostrar uma mensagem de erro ou fazer algo mais
            return "error"; // Ou qualquer outra página de erro que você queira mostrar
        }
    }
}
