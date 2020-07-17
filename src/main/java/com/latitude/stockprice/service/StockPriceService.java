package com.latitude.stockprice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.naming.directory.InvalidAttributesException;


public class StockPriceService {


	/**
	 * Method to get the maximum profit given list of stock prices
	 * we track the following while traversing the array.
	 * - the minimum number traversed so far
     * - the maximum difference found so far 
     *
	 * @param stockPrices
	 * @return
	 * @throws InvalidAttributesException
	 */
	public BigDecimal getMaxProfit(  List<BigDecimal> stockPrices ) throws InvalidAttributesException { 
		BigDecimal maxDiff=BigDecimal.ZERO;
		try {
			BigDecimal minPrice=stockPrices.get(0);

			for (int i = 1; i < stockPrices.size(); i++) {
				BigDecimal diff=stockPrices.get(i).subtract(minPrice);
				// set the maximum difference till now
				if(diff.compareTo(maxDiff)>0) {
					maxDiff=diff;
				}
				// set the minimum price till now
				if(minPrice.compareTo(stockPrices.get(i))>0) {
					minPrice=stockPrices.get(i);
				}
			}
		}catch (Exception e) {
			throw new InvalidAttributesException("Invalid input");
		}
		return maxDiff;

	}

	/**
	 * Generate random list of stockprice list
	 */
	public List<BigDecimal> generateStockPriceList() { 
		List<BigDecimal> stockPriceList =new ArrayList<BigDecimal>(360);
		int[] sp = new Random().ints(8, 0, 234).toArray();
		IntStream.of(sp).forEach(stockPrice->stockPriceList.add(new BigDecimal(stockPrice)));
		return stockPriceList;
	}

}
