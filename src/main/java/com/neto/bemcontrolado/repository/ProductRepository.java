package com.neto.bemcontrolado.repository;


import com.neto.bemcontrolado.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository
        extends JpaRepository<Product,Integer>{
}
