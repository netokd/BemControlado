package com.neto.bemcontrolado.repository;


import com.neto.bemcontrolado.model.Branch;
import com.neto.bemcontrolado.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface InventoryRepository
        extends JpaRepository<Inventory,Integer>{
    Optional<Inventory> findByBranch(Branch branch);
}
