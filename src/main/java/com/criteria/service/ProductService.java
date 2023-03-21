package com.criteria.service;

import java.util.List;

import com.criteria.entities.Product;
import com.criteria.model.SearchingCriteria;
import com.criteria.specification.ProductSpecification;

public interface ProductService {

	public String addProductDetails(Product product);

	public Product getProductDetailsById(Integer productId);

	public List<Product> getAllProduct();

	public Product upateProduct(Product product,Integer productId);

	public String deleteProductById(Integer productId);
	
	public List<Product> search(ProductSpecification specification);

}
