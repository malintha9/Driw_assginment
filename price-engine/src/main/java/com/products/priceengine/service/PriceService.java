package com.products.priceengine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.priceengine.entity.ProductEntity;
import com.products.priceengine.model.Products;
import com.products.priceengine.repository.ProductRepository;

@Service
public class PriceService {

	@Autowired
	ProductRepository productRepository;

	public List<Products> priceList(List<ProductEntity> products, int count) throws Exception {

		List<Products> listOfCartons = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			Products cartons = new Products();
			cartons.setQuantity(i);
			Map<String, Double> MapOfProducts = new HashMap<>();
			for (ProductEntity prodcut : products) {
				MapOfProducts.put(prodcut.getName(), getPriceForProduct(prodcut.getPrice(), prodcut.getUnits(), i));
			}
			cartons.setProducts(MapOfProducts);
			listOfCartons.add(cartons);
		}
		return listOfCartons;
	}

	public double getPriceForProduct(final double cartonPrice, final int unitsPerCarton, final int noOfunits)
			throws Exception {

		double totalAmount = 0;

		// Calculate no of cartons
		int noOfcartons = noOfunits / unitsPerCarton;

		// No of single units after getting cartons
		int units = noOfunits - (noOfcartons * unitsPerCarton);

		// Unit price of a carton
		double unitPrice = getUnitPrice(cartonPrice, unitsPerCarton);

		// Total price for the carton
		double priceForCartons = cartonPrice * noOfcartons;

		// Calculate discount for cartons count exceeds 3 or more
		if (noOfcartons >= 3) {
			priceForCartons = priceForCartons * 0.9;
		}

		// Calculate total amount
		totalAmount = priceForCartons + (units * unitPrice);

		return totalAmount;

	}

	// Calculate unit price
	private double getUnitPrice(final double cartonPrice, final int cartonSize) {
		double unitPrice = 0;
		unitPrice = ((cartonPrice * 130) / 100) / cartonSize;
		return unitPrice;
	}

	/**
	 * Get all the products from the database
	 * 
	 * @return
	 */
	public List<ProductEntity> getProductList() {
		return productRepository.findAll();

	}

	public Optional<ProductEntity> getProductById(Long  id) {
		System.out.println("Product name in the service " + id);
		return productRepository.findById(id);
	}

}
