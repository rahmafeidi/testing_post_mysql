package com.example.projetjee.controller;


import com.example.projetjee.model.Category;
import com.example.projetjee.model.Product;
import com.example.projetjee.repository.CategoryRepository;
import com.example.projetjee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/save")
    public Product createProduct(@RequestBody Product product){
        return  productRepository.save(product);
    }
// ajout product avec id category

    @PostMapping("/savee/{id_category}")
    public Product createProductavec_idcate(@PathVariable Long id_category, @RequestBody Product product){
        Category c= categoryRepository.findById(id_category).orElseThrow(() -> new RuntimeException("id not found"));
        product.setCategory(c);
        return  productRepository.save(product);
    }
    @GetMapping("/all")
    public List<Product> allProduct(){
        return  productRepository.findAll();
    }


    @GetMapping("/getone/{id}")
    public Product Productbyid(@PathVariable Long id){
        return  productRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }


    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        Product c= productRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));

        if(c != null){
            product.setId(id);
            product.setName(product.getName() == null ? c.getName() : product.getName());
            product.setDescription(product.getDescription() == null ? c.getDescription() : product.getDescription());

            return  productRepository.save(product);
        }
        else{
            throw new RuntimeException("error");
        }

    }
    @DeleteMapping("/{id}")
    public HashMap<String,String> deleteprod(@PathVariable Long id){
        HashMap message =  new HashMap();
        try {
            productRepository.deleteById(id);
            message.put("etat","Product deleted");
            return  message;
        }
        catch (Exception e){
            message.put("etat","Error"+e.getMessage());
            return message;
        }
    }
}
