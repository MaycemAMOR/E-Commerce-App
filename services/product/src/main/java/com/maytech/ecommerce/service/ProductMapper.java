package com.maytech.ecommerce.service;

import com.maytech.ecommerce.entity.Category;
import com.maytech.ecommerce.entity.Product;
import com.maytech.ecommerce.model.ProductPurchaseResponse;
import com.maytech.ecommerce.model.ProductRequest;
import com.maytech.ecommerce.model.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        if (request == null) {
            return null;
        }
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder().
                                id(request.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity);
    }
}
