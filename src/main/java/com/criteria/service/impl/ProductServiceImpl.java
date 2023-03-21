package com.criteria.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.criteria.entities.Address;
import com.criteria.entities.Product;
import com.criteria.model.SearchingCriteria;
import com.criteria.repositories.ProductRepository;
import com.criteria.service.ProductService;
import com.criteria.specification.ProductSpecification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public String addProductDetails(Product product) {
		log.error("Working Fine");
		product.setBookingDate(new Date());
		product.setAddress(product.getAddress());
		Address address = product.getAddress();
		address.setProduct(product);
		productRepository.save(product);
		return "Successful...!!!";
	}

	@Override
	public Product getProductDetailsById(Integer productId) {
		Optional<Product> productDetails = productRepository.findById(productId);
		Product product = productDetails.get();
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> allProductsDetails = productRepository.findAll();
		return allProductsDetails;
	}

	@Override
	public Product upateProduct(Product product, Integer productId) {
		product.setProductId(productId);

		Address address = product.getAddress();
		Integer id = product.getAddress().getId();
		System.out.println(id);
		address.setId(id);
		product.setAddress(address);
		Product updatedProductDetails = productRepository.save(product);

		return updatedProductDetails;
	}

	@Override
	public String deleteProductById(Integer productId) {
		productRepository.deleteById(productId);
		return "Deleted Successful...!!!";
	}

	@Override
	public List<Product> search(ProductSpecification specification) {
		List<Product> filterData = this.productRepository.findAll(specification);
		return filterData;
	}

}
