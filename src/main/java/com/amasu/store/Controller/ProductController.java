package com.amasu.store.Controller;

import com.amasu.store.Models.APIResponse;
import com.amasu.store.Models.Product;
import com.amasu.store.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts(){
        log.info("getAllProducts()");
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.OK.value())
                .data(productService.getAllProducts())
                .build();
        return new ResponseEntity(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public @ResponseBody APIResponse getByType(@PathVariable String type){
        log.info("Retrieving products with type: " + type);
        List<Product> productListByType = productService.getProductsByType(type);
        return APIResponse.builder()
                .status(HttpStatus.OK.value())
                .message(productListByType.size() + " products retrieved")
                .data(productListByType)
                .build();
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product){
        log.info("Saving individual product :::" + product.getProductName());
        productService.addProduct(product);
    }

    @PostMapping("/addProducts")
    public void addProduct(@RequestBody List<Product> productList){
        log.info("Saving " + productList.size() + " new products to the database");
        productService.addProduct(productList);
    }

    @DeleteMapping("/deleteProduct")
    public @ResponseBody APIResponse delete(@RequestBody Product product){
        productService.deleteProduct(product);
        return APIResponse.builder()
                .status(HttpStatus.OK.value())
                .message(product.getProductName() + " deleted").build();
    }

    @DeleteMapping("/deleteProducts")
    public @ResponseBody APIResponse delete(@RequestBody List<Product> productList){
        productService.deleteProduct(productList);
        return APIResponse.builder()
                .status(HttpStatus.OK.value())
                .message("products have been deleted").build();
    }
}
