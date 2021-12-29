package com.amasu.store.Service;

import com.amasu.store.Models.Product;
import com.amasu.store.Repository.ProductRepository;
import com.amasu.store.Util.ProductUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        product.setDate(LocalDate.now());
        productRepository.save(product);
    }

    public void addProduct(List<Product> productList){
        ProductUtils.setDateProductList(productList);
        productRepository.saveAll(productList);
    }

    @Transactional
    public void deleteProduct(Product product) {
        productRepository.deleteByProductName(product.getProductName());
        log.info(product.getProductName() + " has been deleted");
    }

    @Transactional
    public void deleteProduct(List<Product> productList) {
        productList.forEach(product -> {
            productRepository.deleteByProductName(product.getProductName());
            log.info(product.getProductName() + " has been deleted");
        });
    }

    public List<Product> getProductsByType(String type) {
        return getAllProducts().stream().filter(product -> product.getType().toLowerCase().equals(type.toLowerCase())).collect(Collectors.toList());
    }
}
