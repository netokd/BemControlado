package com.neto.bemcontrolado.service;

import com.neto.bemcontrolado.model.Branch;
import com.neto.bemcontrolado.repository.BranchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/branch")
public class BranchService {

    private final BranchRepository branchRepository;
    public BranchService(BranchRepository branchRepository, BranchRepository branchRepository1){this.branchRepository = branchRepository;}

    public static void main(String[] args){ SpringApplication.run(BranchService.class, args); }

    @GetMapping
    public List<Branch> getBranch(){ return branchRepository.findAll(); }

    record NewBranchRequest(
        String name,
        String address,
        String responsible,
        String cnpj
    ){}

    @PostMapping
    public void addBranch(@RequestBody NewBranchRequest request){
        Branch branch = new Branch();
        branch.setName(request.name());
        branch.setAddress(request.address);
        branch.setResponsible(request.responsible);
        branch.setCnpj(request.cnpj);
        branchRepository.save(branch);
    }

    @DeleteMapping("{branchId} ")
    public void deleteCustomer(@PathVariable("branchId") Integer id){
        branchRepository.deleteById(id);
    }

    @GetMapping("/view")
    public String viewBranch(Model model){
        List<Branch> branch = branchRepository.findAll();
        model.addAttribute("branchList", branch);
        return "branch";
    }
}
