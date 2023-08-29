package com.neto.bemcontrolado.repository;

import com.neto.bemcontrolado.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository
        extends JpaRepository<Branch,Integer> {

}
