package com.neto.bemcontrolado.repository;


import com.neto.bemcontrolado.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InventoryRepository
        extends JpaRepository<Inventory,Integer>{
}
