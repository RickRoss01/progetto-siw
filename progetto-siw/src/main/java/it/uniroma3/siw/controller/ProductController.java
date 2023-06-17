package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.service.ProductService;

@Controller
public class ProductController{

    @Autowired
    ProductService productService;

    @GetMapping(value = "/products")
    private String getProducts(Model model){
        model.addAttribute("products",this.productService.getAllProducts());
        return "products.html";
    }

    @GetMapping(value = "/formNewProduct")
    private String formNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "product.html";            
    }
    
    @GetMapping(value = "/product/{productId}")
    private String getProduct(@PathVariable("productId") Long productId, Model model){
        Product product = this.productService.getProduct(productId);
        model.addAttribute("product",product);
        return "product.html";
    }
    
    @PostMapping(value = "/newProduct")
    private String newProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model){
        Product newProduct = this.productService.newProduct(product, bindingResult);
        model.addAttribute("product", newProduct);
        return "product.html";
    }
    
    @PostMapping(value = "/updateProduct")
    private String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model){
        Product newProduct = this.productService.updateProduct(product, bindingResult);
        model.addAttribute("product", newProduct);
        return "product.html";
    }


    @GetMapping(value = "/deleteProduct/{id}")
    private String deleteProduct(@PathVariable("id") Long productId,Model model){
        this.productService.deleteProduct(productId);
        return getProducts(model);
    }

}