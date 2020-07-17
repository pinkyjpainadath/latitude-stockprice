

package com.latitude.stockprice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import javax.naming.directory.InvalidAttributesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import com.latitude.stockprice.service.StockPriceService;

class ApplicationTests {
	StockPriceService spService;

	@BeforeEach
	void init() {
		spService = new StockPriceService();
	}
	
	@ParameterizedTest
	@EmptySource
	@DisplayName("Test Empty Input")
	void checkEmptyInput(List<BigDecimal> stockPrices) {
		assertThrows(InvalidAttributesException.class, () -> {
			spService.getMaxProfit(stockPrices);
		    });
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Test Null and Empty Input")
	void checkNullAndEmptyInput(List<BigDecimal> stockPrices) {
		assertThrows(InvalidAttributesException.class, () -> {
			spService.getMaxProfit(stockPrices);
		    });
	}
	
	@Test
	@DisplayName("Test Profit Scneario")
	void checkProfitScenario() {
		int[] sp = {1, 3, 5, 2, 15};
		List<BigDecimal> stockPrices =new ArrayList<BigDecimal>(360);
		IntStream.of(sp).forEach(stockPrice->stockPrices.add(new BigDecimal(stockPrice)));
		try {
			assertEquals(new BigDecimal(14),spService.getMaxProfit(stockPrices));
		} catch (InvalidAttributesException e) {
		    assertEquals("Invalid input", e.getMessage() );
		}
	}
	
	@Test
	@DisplayName("Test Loss Scneario")
	void checkLossScenario() {
		double[] sp= {100.9341, 97.123, 80.22, 80.22, 50.00,49.0,49.0,38,9.901};
		List<BigDecimal> stockPrices =new ArrayList<BigDecimal>(360);
		DoubleStream.of(sp).forEach(stockPrice->stockPrices.add(new BigDecimal(stockPrice)));
		try {
			assertEquals(BigDecimal.ZERO,spService.getMaxProfit(stockPrices));
		} catch (InvalidAttributesException e) {
		    assertEquals("Invalid input", e.getMessage() );

		}
	}
	
	@Test
	@DisplayName("Test Flat Price Scneario")
	void checkFlatPriceScenario() {
		int[] sp = {300, 300, 300, 300, 300,300,300,300};
		List<BigDecimal> stockPrices =new ArrayList<BigDecimal>(360);
		IntStream.of(sp).forEach(stockPrice->stockPrices.add(new BigDecimal(stockPrice)));
		try {
			assertEquals(BigDecimal.ZERO,spService.getMaxProfit(stockPrices));
		} catch (InvalidAttributesException e) {
		    assertEquals("Invalid input", e.getMessage() );
		}
	}
	
	@Test
	@DisplayName("Test Invalid Input")
	void checkInvalidInput() {
		int[] sp = {23};
		List<BigDecimal> stockPrices =new ArrayList<BigDecimal>(360);
		IntStream.of(sp).forEach(stockPrice->stockPrices.add(new BigDecimal(stockPrice)));
		try {
			assertEquals(BigDecimal.ZERO,spService.getMaxProfit(stockPrices));
		} catch (InvalidAttributesException e) {
		    assertEquals("Invalid input", e.getMessage() );
		}
	}
}
