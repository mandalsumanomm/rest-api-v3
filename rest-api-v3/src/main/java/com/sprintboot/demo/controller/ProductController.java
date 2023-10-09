package com.sprintboot.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sprintboot.demo.model.Product;

@RestController
public class ProductController {

	public static Map<String, Product> productRepo = new HashMap<>();
		
	static {
		Product honey = new Product();		
		honey.setId("1");
		honey.setName("heney");
		productRepo.put(honey.getId(), honey);
		
		Product almond =new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
		
		Product milk = new Product();
		milk.setId("3");
		milk.setName("Amul");
		productRepo.put(milk.getId(), milk);
		
		Product nuts = new Product();
		nuts.setId("4");
		nuts.setName("Kaju");
		productRepo.put(nuts.getId(), nuts);
		
		Product oil = new Product();
		oil.setId("5");
		oil.setName("Coconut oil");
		productRepo.put(oil.getId(), oil);
				
	}
	
	//delete data
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id")String id){
		productRepo.remove(id);
		return new ResponseEntity<>("Product delete successfully", HttpStatus.OK );
	}
	
	//put or update
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,@RequestBody Product product){
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
	}
	
	//post data	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	
	//get data
	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct(){
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}
	
	
}

/*
ResponseEntity: This is a generic class provided by the Spring Framework.
It's used to represent the entire HTTP response, including the status code,
headers, and body.

<Object>: The generic type parameter <Object> signifies the type of the body
of the response. In this case, it's Object, meaning it can represent any type
of response body.

@PathVariable: annotation is used to retrieve data from the URL path.

HttpStatus.OK: which corresponds to a 200 OK status. It indicates that the request was successful.

new ResponseEntity<>(): Instantiates a new ResponseEntity object.

@RequestMapping: is an annotation that maps HTTP requests to handler methods in Spring MVC.
*/
