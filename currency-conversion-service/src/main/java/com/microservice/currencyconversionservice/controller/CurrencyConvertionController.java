package com.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.currencyconversionservice.CurrencyConvertionBean;
import com.microservice.currencyconversionservice.CurrencyExchangeServiceProxy;


@RestController
public class CurrencyConvertionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("currency-converter/from/{from}/to/{to}/{quantity}")
	public CurrencyConvertionBean convertCurrency(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConvertionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConvertionBean.class, uriVariables );
		
		CurrencyConvertionBean response = responseEntity.getBody();
		
		return new CurrencyConvertionBean(response.getId(), from, to, response.getConvertionMultiple(), quantity, quantity.multiply(response.getConvertionMultiple()), response.getPort());	
	}
	
	@GetMapping("currency-converter-feign/from/{from}/to/{to}/{quantity}")
	public CurrencyConvertionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
			
		CurrencyConvertionBean response = proxy.retriveExchangeValue(from, to);
		
		
		return new CurrencyConvertionBean(response.getId(), from, to, response.getConvertionMultiple(), quantity, quantity.multiply(response.getConvertionMultiple()), response.getPort());	
	}
	
}
