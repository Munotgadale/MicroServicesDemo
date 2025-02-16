package com.Demo.Controller;

import com.Demo.Service.ProductService;
import com.Demo.dto.ProductRequest;
import com.Demo.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String creteProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return "Product Saved Successfully";
    }

    @GetMapping("/get-products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
       return productService.getAllProducts();
    }
}
