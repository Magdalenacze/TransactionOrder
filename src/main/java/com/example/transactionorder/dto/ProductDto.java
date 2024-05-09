package com.example.transactionorder.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ProductDto {

    private String name;
    private Integer quantity;
}
