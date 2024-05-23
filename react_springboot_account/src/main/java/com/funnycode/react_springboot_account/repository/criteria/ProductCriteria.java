package com.funnycode.react_springboot_account.repository.criteria;

import com.funnycode.react_springboot_account.dto.product.ProductDTOFilter;
import com.funnycode.react_springboot_account.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ProductCriteria {
    public final EntityManager em;


    public Map<String,Object> getAllProducts(ProductDTOFilter filter) {
        StringBuilder query = new StringBuilder("select p from Product p left join p.colors c where 1=1");

        Map<String,Object> param = new HashMap<>();

        if (filter.getType()!=null){
            query.append(" and p.type = :type");
            param.put("type", filter.getType());
        }
        if (filter.getColor()!=null){
            query.append(" and c.name in (:color)");
            param.put("color", filter.getColor());
        }
        if (filter.getName()!=null){
            query.append(" and p.name like :name");
            param.put("name", "%" +filter.getName() +"%");
        }
        if (filter.getMinPrice()!=null){
            query.append(" and p.price >= :minPrice");
            param.put("minPrice", filter.getMinPrice());
        }
        if (filter.getMaxPrice()!=null){
            query.append(" and p.price <= :maxPrice");
            param.put("maxPrice", filter.getMaxPrice());
        }


        TypedQuery<Product> tQuery = em.createQuery(query.toString(), Product.class);
        Query cQuery = em.createQuery(query.toString().replace("select p", "select count(p.id)"));

        param.forEach((k,v) -> {
            tQuery.setParameter(k, v);
            cQuery.setParameter(k, v);
        });

        tQuery.setFirstResult(filter.getOffset());
        tQuery.setMaxResults(filter.getLimit());

        Long countProduct = (Long) cQuery.getSingleResult();
        List<Product> productList = tQuery.getResultList();

        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("countProduct", countProduct);
        wrapper.put("productList", productList);

        return wrapper;

    }
}
