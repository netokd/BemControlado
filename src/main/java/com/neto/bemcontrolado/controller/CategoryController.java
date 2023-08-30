package com.neto.bemcontrolado.controller;

import com.neto.bemcontrolado.model.Category;
import com.neto.bemcontrolado.model.Category;
import com.neto.bemcontrolado.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate ;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, RestTemplate restTemplate) {
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/categoria")
    public String category(Model model){
        List<Category> category = categoryRepository.findAll();
        model.addAttribute("categoryList", category);
        return "category/category";
    }


    @GetMapping("/adicionar-categoria")
    public String showAddCategoryForm(Model model
    ) {
        model.addAttribute("category", new Category());
        return "category/create-category";
    }

    @GetMapping("/atualizar-categoria/{categoryId}")
    public String editCategoryForm(@PathVariable Integer categoryId, Model model){
        try {
            Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
            model.addAttribute("category", category);
            return "category/update-category";
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Lança a exceção novamente para que o Spring possa tratá-la
        }
    }

    @PostMapping("/atualizar-categoria/{categoryId}")
    public String updateCategory(@PathVariable Integer categoryId, @ModelAttribute Category updatedCategory) {
        // Busque o cliente existente pelo ID
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));

        // Atualize os dados do cliente existente com os dados do formulário
        existingCategory.setName(updatedCategory.getName());

        // Salve as atualizações no banco de dados
        categoryRepository.save(existingCategory);

        return "redirect:/categoria"; // Redirecione para a página de listagem após a atualização
    }

    @GetMapping("/excluir-categoria/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId, RestTemplate restTemplate){
        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    "http://localhost:8080/api/v1/category/" + categoryId,
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );


            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return "redirect:/categoria"; // Redireciona para a página de listagem após a exclusão
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

