package com.neto.bemcontrolado.controller;

import com.neto.bemcontrolado.model.Branch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.neto.bemcontrolado.repository.BranchRepository;
import java.util.List;

@Controller
public class BranchController {
    private final BranchRepository branchRepository;


    public BranchController(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @GetMapping("/filial")
    public String branch(Model model){
        List<Branch> branch = branchRepository.findAll();
        model.addAttribute("branchList", branch);
        return "branch";
    }
}
