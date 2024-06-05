package com.maytech.ecommerce.model;

import com.maytech.ecommerce.entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
