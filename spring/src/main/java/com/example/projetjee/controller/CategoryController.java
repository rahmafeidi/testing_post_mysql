package com.example.projetjee.controller;

import com.example.projetjee.model.Category;
import com.example.projetjee.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRepository;



    @PostMapping("/save")
    public Category createcategory(@RequestBody Category category){
        return  categoryRepository.save(category);
    }

    @GetMapping("/all")
    public List<Category> allcategory(){
        return  categoryRepository.findAll();
    }


    @GetMapping("/getone/{id}")
    public Category categorybyid(@PathVariable Long id){
        return  categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }


    @PutMapping("/update/{id}")
    public Category updatecategoy(@PathVariable Long id,@RequestBody Category category){
        Category c= categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));

        if(c != null){
            category.setId(id);
            category.setTitle(category.getTitle() == null ? c.getTitle() : category.getTitle());
            category.setDescription(category.getDescription() == null ? c.getDescription() : category.getDescription());

            return  categoryRepository.save(category);
        }
        else{
            throw new RuntimeException("error");
        }

    }
    @DeleteMapping("/{id}")
    public HashMap<String,String> deleteprod(@PathVariable Long id){
        HashMap message =  new HashMap();
        try {
            categoryRepository.deleteById(id);
            message.put("etat","category deleted");
            return  message;
        }
        catch (Exception e){
            message.put("etat","Error"+e.getMessage());
            return message;
        }
    }
}
