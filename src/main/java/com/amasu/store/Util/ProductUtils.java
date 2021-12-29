package com.amasu.store.Util;

import com.amasu.store.Models.Product;

import java.time.LocalDate;
import java.util.List;

public class ProductUtils {
    public static void setDateProductList(List<Product> productList){
        productList.forEach(product -> {
            product.setDate(LocalDate.now());
        });
    }
}
