package com.funnycode.react_springboot_account.service;

import com.funnycode.react_springboot_account.dto.product.ProductDTO;
import com.funnycode.react_springboot_account.dto.product.ProductDTOAdd;
import com.funnycode.react_springboot_account.dto.product.ProductDTOFilter;
import com.funnycode.react_springboot_account.dto.product.ProductDTOUpdate;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public ProductDTO getProductById(int id);

    public Map<String,Object> getAllProducts(ProductDTOFilter productDTOFilter);

    public ProductDTO newProduct(ProductDTOAdd newProduct);

    public ProductDTO updateProduct(int id, ProductDTOUpdate update);

    public ProductDTO deleteProduct(int id);
}
