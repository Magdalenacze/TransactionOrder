package com.example.transactionorder.repository;

import com.example.transactionorder.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

   Optional<ProductEntity> findByName(String name);

   Long deleteByName(String name);
}
