package com.products.priceengine.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.priceengine.entity.ProductEntity;
import com.products.priceengine.exception.priceNotCalcualtedException;
import com.products.priceengine.model.Products;
import com.products.priceengine.service.PriceService;
import com.products.priceengine.util.Util;

@RestController
public class PriceEngineController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass())
	
;	@Autowired
	PriceService priceService;

	@GetMapping("/productprices")
	public List<Products> getProdcutPriceList(@RequestParam int Quantity) {

		try {
			logger.info(Util.TWO_VALUES, Util.PRODUCT_PRICE_LIST_CONTROLLER, Quantity);
			List<ProductEntity> prodcuts = priceService.getProductList();
			if(prodcuts.isEmpty()) {
				return new ArrayList<>();
			}
			return priceService.priceList(prodcuts, Quantity);
		} catch (Exception ex) {
			logger.error(Util.TWO_VALUES, Util.CALCULATION_NOT_DONE, ex.getMessage());
			throw new priceNotCalcualtedException();
		}
	}

	@GetMapping("/priceperunits")
	public double getPrice(@RequestParam int Quantity, @RequestParam long productId) {

		double result = 0;
		try {
			ProductEntity productEntity = null;
			Optional<ProductEntity> productOptional = priceService.getProductById(productId);
			if (productOptional.isPresent()) {
				productEntity = productOptional.get();
			}
			if(productEntity == null) {
				return result;
			}
			result = priceService.getPriceForProduct(productEntity.getPrice(), productEntity.getUnits(), Quantity);			
			return result;

		} catch (Exception ex) {
			logger.error(Util.TWO_VALUES, Util.CALCULATION_NOT_DONE, ex.getMessage());
			throw new priceNotCalcualtedException();
		}
	}
	
	@GetMapping("/products")
	public List<ProductEntity> getProdcuts() {
		return priceService.getProductList();
	}
}
