package com.latitude.stockprice;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import com.latitude.stockprice.service.StockPriceService;

/**
 * Main class invokes service to
 * 	-  generate random stock price list
 *  -  use this price list as input to calculate the best profit
 *
 */
public class Application  {


	public static void main(String[] args) {


		StockPriceService spService = new StockPriceService();

		List<BigDecimal> stockPrices=spService.generateStockPriceList();
		BigDecimal maxProfit;
		try {
			maxProfit = spService.getMaxProfit(stockPrices);
			System.out.println("Given Stock Price list: " + Arrays.toString(stockPrices.toArray()));
			System.out.println("Maximum Profit: " + maxProfit.toString());
		} catch (InvalidAttributesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
