package com.neto.bemcontrolado.repository;


import com.neto.bemcontrolado.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category,Integer> {

}
