package com.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.limitsservice.bean.LimitConfigration;
import com.microservice.limitsservice.config.Config;

@RestController
public class limitConfigController {

	@Autowired
	private Config config;
	
	@GetMapping("/limits")
	public LimitConfigration retriveLimitsFromConfiguration() {
		return new LimitConfigration(config.getMax(), config.getMin());
	}
}
