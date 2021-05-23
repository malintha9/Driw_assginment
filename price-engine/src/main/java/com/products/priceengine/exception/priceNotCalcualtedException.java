package com.products.priceengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.products.priceengine.util.Util;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class priceNotCalcualtedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return Util.CALCULATION_NOT_DONE;
	}

}
