package com.criteria.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.criteria.entities.Product;
import com.criteria.model.SearchingCriteria;
import com.criteria.operation.SearchingOperation;
import com.criteria.repositories.ProductRepository;
import com.criteria.service.ProductService;
import com.criteria.specification.ProductSpecification;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<?> addProductDetails(@RequestBody Product product) {
		String message = this.productService.addProductDetails(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping("/get/{productId}")
	public ResponseEntity<?> getProductDetailsById(@PathVariable Integer productId) {
		Product productDetails = this.productService.getProductDetailsById(productId);
		return ResponseEntity.ok(productDetails);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllProduct() {
		List<Product> allProductDetails = this.productService.getAllProduct();
		return ResponseEntity.ok(allProductDetails);
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity<?> updateCompanyDetails(@RequestBody Product product, @PathVariable Integer productId) {
		Product upateProductDetails = this.productService.upateProduct(product, productId);
		return ResponseEntity.ok(upateProductDetails);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteCompantDetail(@PathVariable Integer productId) {
		String productDetails = this.productService.deleteProductById(productId);
		return ResponseEntity.ok(productDetails);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "company", required = false) String company,
			@RequestParam(name = "deliveryDate", required = false) String deliveryDate,
			@RequestParam(name = "productCost" , required = false) Integer[] productCost
	) {
		
		ProductSpecification specification = new ProductSpecification();
		
		if (productName != null) {
			specification.add(new SearchingCriteria("productName" , productName , SearchingOperation.EQUAL, null));
		}
		if(company != null) {
			specification.add(new SearchingCriteria("company" , company , SearchingOperation.NOT_EQUAL,null));
		}
		if(deliveryDate != null) {
			specification.add(new SearchingCriteria("deliveryDate" ,deliveryDate , SearchingOperation.GREATER_THAN , null ));
		}
		if(productCost != null && productCost.length > 0) {
			specification.add(new SearchingCriteria("productCost" , null , SearchingOperation.IN , Arrays.asList(productCost)));
		}
		
		List<Product> listOfProduct = this.productService.search(specification);
		return ResponseEntity.ok(listOfProduct);
	}

}
