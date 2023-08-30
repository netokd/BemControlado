package com.neto.bemcontrolado.service;


import com.neto.bemcontrolado.model.Branch;
import com.neto.bemcontrolado.model.Category;
import com.neto.bemcontrolado.repository.CategoryRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/category")
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getCategory(){return categoryRepository.findAll();}

    record NewCategoryRequest(
            String name
    ){}

    @PostMapping
    public void addCategory(@RequestBody NewCategoryRequest request){
        Category category = new Category();
        category.setName(request.name());
        categoryRepository.save(category);
    }
    @DeleteMapping("{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Integer id){ categoryRepository.deleteById(id); }

    @GetMapping("/view")
    public String viewCategory(Model model){
        List<Category> category = categoryRepository.findAll();
        model.addAttribute("categoryList", category);
        return "category";
    }

}
