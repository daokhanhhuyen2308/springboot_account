package com.funnycode.react_springboot_account.service.impl;

import com.funnycode.react_springboot_account.dto.product.ProductDTO;
import com.funnycode.react_springboot_account.dto.product.ProductDTOAdd;
import com.funnycode.react_springboot_account.dto.product.ProductDTOFilter;
import com.funnycode.react_springboot_account.dto.product.ProductDTOUpdate;
import com.funnycode.react_springboot_account.entity.*;
import com.funnycode.react_springboot_account.exception.CustomHandleException;
import com.funnycode.react_springboot_account.mapper.ProductMapper;
import com.funnycode.react_springboot_account.repository.ColorRepository;
import com.funnycode.react_springboot_account.repository.ProductRepository;
import com.funnycode.react_springboot_account.repository.SizeRepository;
import com.funnycode.react_springboot_account.repository.criteria.ProductCriteria;
import com.funnycode.react_springboot_account.service.AccountService;
import com.funnycode.react_springboot_account.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ColorRepository colorRepository;
    SizeRepository sizeRepository;
    ProductCriteria productCriteria;
    AccountService accountService;

    @Override
    public ProductDTO getProductById(int id) {
        Optional<Product> getProductById = productRepository.findById(id);
        if (getProductById.isEmpty()) {
            throw CustomHandleException.notFountException("id is not found");
        }
        return ProductMapper.toProductDTO(getProductById.get());
    }

    @Override
    public Map<String, Object> getAllProducts(ProductDTOFilter productDTOFilter) {
        Map<String, Object> result = productCriteria.getAllProducts(productDTOFilter);
        Long countProduct = (Long) result.get("countProduct");
        System.out.println(countProduct);
        List<Product> list = (List<Product>) result.get("productList");
        System.out.println(list);
        List<ProductDTO> productList = list.stream().map(ProductMapper::toProductDTO).toList();
        System.out.println(productList);

        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("countProduct", countProduct);
        wrapper.put("productList", productList);
        return wrapper;
    }

    @Override
    @Transactional
    public ProductDTO newProduct(ProductDTOAdd newProduct) {


        Product product = ProductMapper.toProduct(newProduct);

        if ((newProduct.getName()) == null) {
            throw CustomHandleException.badRequestException("Product name cannot be empty");
        }

        if (newProduct.getImage() == null) {
            throw new IllegalStateException("Product image is not null");
        }

        if (newProduct.getPrice() <= 0) {
            throw new IllegalStateException("Product price invalid");
        }

        if (newProduct.getType() == null) {
            throw new IllegalStateException("Product type is not null");
        }

        Set<Color> colors = new HashSet<>();
        Set<String> stringColors = newProduct.getColors();


        if (!stringColors.isEmpty()) {

            for (String name : stringColors) {

                Optional<Color> colorOptional = colorRepository.findAllByNameIgnoreCase(name);

                if (colorOptional.isPresent()) {
                    colors.add(colorOptional.get());
                } else {
                    throw CustomHandleException.notFountException("This color is not found, please check again");
                }
            }
            product.setColors(colors);
        } else {
            throw CustomHandleException.badRequestException("Product colors cannot be empty");
        }


        Set<Size> sizes = new HashSet<>();
        Set<String> sizeStrings = newProduct.getSizes();

        if (!sizeStrings.isEmpty()) {
            for (String name : sizeStrings) {
                Optional<Size> sizeOptional = sizeRepository.findAllByNameIgnoreCase(name);
                if (sizeOptional.isPresent()) {
                    sizes.add(sizeOptional.get());

                } else {

                    throw CustomHandleException.notFountException("This size is not found, please check again");

                }
            }
            product.setSizes(sizes);

        } else {

            throw CustomHandleException.badRequestException("Product sizes cannot be empty");
        }

        product = productRepository.save(product);

        return ProductMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTOUpdate update) {

        return null;
    }

    @Override
    public ProductDTO deleteProduct(int id) {
//        Optional<Product> productOptional = productRepository.findById(id);
//
//        if (productOptional.isEmpty()) {
//            throw CustomHandleException.notFountException("Product not found");
//        }
//
//        Product product = productOptional.get();
//        productRepository.deleteById(id);
//        productRepository.save(product);
//        return ProductMapper.toProductDTO(product);
        return null;
    }

    private void checkRoleAccount() {
        Account account = accountService.getAccountLoggedIn();

        if (account == null) {
            throw CustomHandleException.badRequestException("User is not logged in");
        }

        Set<Role> roles = account.getRoles();

        boolean isAdminOrManager = false;

        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase("ADMIN") || role.getName().equalsIgnoreCase("MANAGER")) {
                isAdminOrManager = true;
                break;
            }
        }

        if (!isAdminOrManager) {
            throw CustomHandleException.unauthorizedException("Unauthorized user. Only admins and managers are allowed to add products");
        }
    }
}
