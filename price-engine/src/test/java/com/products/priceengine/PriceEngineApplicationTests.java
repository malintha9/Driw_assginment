package com.products.priceengine;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.products.priceengine.contoller.PriceEngineController;
import com.products.priceengine.entity.ProductEntity;
import com.products.priceengine.service.PriceService;

@WebMvcTest(PriceEngineController.class)
@RunWith(SpringRunner.class)
class PriceEngineApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PriceService priceService;

	List<ProductEntity> entities;

	@BeforeEach
	void setUp() {

		this.entities = new ArrayList<>();

		ProductEntity entity = new ProductEntity();
		entity.setId(1);
		entity.setName("Penguin-ears");
		entity.setPrice(175);
		entity.setUnits(20);

		ProductEntity entity2 = new ProductEntity();
		entity2.setId(2);
		entity2.setName("Horseshoe");
		entity2.setPrice(825);
		entity2.setUnits(5);

		entities.add(entity);
		entities.add(entity2);
	}

	@Test
	void fetchAllProducts() throws Exception {
		when(priceService.getProductList()).thenReturn(entities);

		mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	void fetchPorducPricePerUnits() throws Exception{
		final Long productId = 1L;
		final ProductEntity entity = new ProductEntity(1l, "Penguin-ears", 175, 20);
		
		when(priceService.getProductById(productId)).thenReturn(Optional.of(entity));
		when(priceService.getPriceForProduct(entity.getPrice(), entity.getUnits(), 10)).thenReturn(87.5);
		
		mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
