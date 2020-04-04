package com.microservice.currencyexchangeservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.currencyexchangeservice.ExchangeValue;

public interface ExchangeValueService extends JpaRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from,String To);
}
