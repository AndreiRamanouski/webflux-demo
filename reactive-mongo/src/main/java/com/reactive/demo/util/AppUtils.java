package com.reactive.demo.util;

import com.reactive.demo.dto.ProductDto;
import com.reactive.demo.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {


    public static ProductDto entityToDto(Product product){
        ProductDto returnedValue = new ProductDto();
        BeanUtils.copyProperties(product,returnedValue);
        return returnedValue;
    }

    public static Product dtoToEntity(ProductDto productDto){
        Product returnedValue = new Product();
        BeanUtils.copyProperties(productDto,returnedValue);
        return returnedValue;
    }
}

