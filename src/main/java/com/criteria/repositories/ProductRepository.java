package com.criteria.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.criteria.entities.Product;
import com.criteria.model.SearchingCriteria;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> , JpaSpecificationExecutor<Product>{


}
