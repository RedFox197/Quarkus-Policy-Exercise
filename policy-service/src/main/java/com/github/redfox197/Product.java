package com.github.redfox197;

public record Product(
        Long id,
        String code,
        String name,
        double price
) {
}
