package com.company.exchange.controller;

import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.company.exchange.service.DataExchangeService;

@RestController
public class DataExchangeController {

	@Autowired
	private DataExchangeService service;

	public void longPollDataExchange(long httpPollTimer, long dbPollTimer) {
		Timer timer = new Timer();
		TimerTask exchangeApi = new TimerTask() {

			@Override
			public void run() {
				try {
					service.getDataFromExchange();
				} catch (Exception e) {
				}
				postDataToExchange();
			}

			public void postDataToExchange() {
				try {
					service.postDataToExchange();
				} catch (Exception e) {
				}
			}
		};
		timer.schedule(exchangeApi, httpPollTimer, dbPollTimer);
	}
}
