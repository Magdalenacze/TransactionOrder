package com.example.transactionorder.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class OrderDto {

    private String productName;
    private Integer quantityOrdered;
}
