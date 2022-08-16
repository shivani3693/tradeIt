package tradeit.shivani.shah.stocks.test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tradeit.shivani.shah.stocks.service.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
class StockServiceTest {

	Logger log = LoggerFactory.getLogger(StockServiceTest.class);

	@Autowired
	private StockService stockService;

	@BeforeEach
	public void setup() {
		log.info("Loading setup");
	}

	@Test
	public void checkValidStock() {
		Assert.assertTrue(stockService.checkIfStockIsValid("GOOG")!=null);
	}
	

	@Test
	public void checkInvalidStock() {
		Assert.assertTrue(stockService.checkIfStockIsValid("GOssOG")==null);
	}
	
	@Test
	public void fetchHistoricalDataForStock() {
		Assert.assertTrue(stockService.fetchHistoricalDataForStock("GOOG").size()!=0);
	}
	
}
