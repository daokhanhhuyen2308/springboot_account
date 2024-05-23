package com.funnycode.react_springboot_account.controller;

import com.funnycode.react_springboot_account.dto.product.ProductDTO;
import com.funnycode.react_springboot_account.dto.product.ProductDTOAdd;
import com.funnycode.react_springboot_account.dto.product.ProductDTOFilter;
import com.funnycode.react_springboot_account.dto.product.ProductDTOUpdate;
import com.funnycode.react_springboot_account.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ProductDTO addProduct(@RequestBody ProductDTOAdd newProduct) {
        return productService.newProduct(newProduct);
    }

    @GetMapping("/getAll")
    public Map<String, Object> getAllProducts(
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "color", required = false) String color,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "limit", defaultValue = "8") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset) {

        ProductDTOFilter productDTOFilter = ProductDTOFilter.builder()
                .type(type).color(color).name(name).minPrice(minPrice)
                .maxPrice(maxPrice).limit(limit).offset(offset).build();
        return productService.getAllProducts(productDTOFilter);
    }


    @PutMapping("/update/{id}")
    public ProductDTO updateProduct(@PathVariable int id, @RequestBody ProductDTOUpdate update) {
        return productService.updateProduct(id, update);
    }

    @DeleteMapping("/delete/{id}")
    public ProductDTO deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

}
