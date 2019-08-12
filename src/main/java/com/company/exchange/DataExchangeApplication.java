package com.company.exchange;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.exchange.controller.DataExchangeController;

@SpringBootApplication
public class DataExchangeApplication {

	@Autowired
	private DataExchangeController controller;

	public static void main(String[] args) {
		SpringApplication.run(DataExchangeApplication.class, args);
	}

	@PostConstruct
	public void initiate() {
		long httpPollTimer = 20000;
		long dbPollTimmer = 30000;
		controller.longPollDataExchange(httpPollTimer, dbPollTimmer);
	}

}
