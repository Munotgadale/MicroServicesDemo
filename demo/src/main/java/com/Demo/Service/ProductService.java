package com.Demo.Service;


import com.Demo.dto.ProductRequest;
import com.Demo.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    public void createProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProducts();
}
