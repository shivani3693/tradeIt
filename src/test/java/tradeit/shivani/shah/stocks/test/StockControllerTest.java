package tradeit.shivani.shah.stocks.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StockControllerTest {

	Logger log = LoggerFactory.getLogger(StockControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		log.info("Loading setup");
	}

	@Test
	public void fetchStockData() throws Exception {

		this.mockMvc.perform(get("http://localhost:3000/api/stocks/fetch/GOOG")).andDo(print())
				.andExpect(status().isOk());
	}

}
