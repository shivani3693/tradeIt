package tradeit.shivani.shah.stocks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tradeit.shivani.shah.stocks.dto.TransferObject;
import tradeit.shivani.shah.stocks.model.StockTimeline;
import tradeit.shivani.shah.stocks.model.Stocks;
import tradeit.shivani.shah.stocks.repository.StockTimelineRepository;
import tradeit.shivani.shah.stocks.repository.StocksRepository;
import tradeit.shivani.shah.stocks.util.APIConstants;
import tradeit.shivani.shah.stocks.util.APIUtil;
import tradeit.shivani.shah.stocks.util.AppConstants;
import tradeit.shivani.shah.stocks.util.DateUtil;

@Service(value = "stockService")
public class StockService {

	private Logger log = LoggerFactory.getLogger(StockService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.host.baseurl}")
	String baseURL;

	@Value("${api.host.key}")
	String apiKey;

	@Value("${api.daily.historical.key}")
	String dailyHistoricalKey;

	@Value("${api.search.key}")
	String searchKey;

	@Autowired
	StockTimelineRepository stockTimelineRepository;

	@Autowired
	StocksRepository stocksRepository;

	public TransferObject fetchAverageDataForStock(String code) {
		log.debug("Fetching information of all stocks");
		TransferObject response = new TransferObject();
		List<StockTimeline> stocks = fetchHistoricalDataForStock(code);
		log.info("Stocks size is {}", stocks.size());
		if (!stocks.isEmpty()) {
			response = fetchAverageData(stocks);
			response.setStatusCode(AppConstants.SUCCESS);
		} else {
			response.setStatusCode(AppConstants.ERROR);
			response.setStatusDesc(AppConstants.ERROR_DESC);
		}
		return response;
	}

	public List<StockTimeline> fetchHistoricalDataForStock(String code) {
		List<StockTimeline> historicalStocks = new ArrayList<>();

		// Check for code validity
		Stocks stockObj = checkIfStockIsValid(code);
		if (stockObj != null) {
			// Fetching last week
			List<String> dates = DateUtil.fetchLastWeekDates();
			dates.forEach(date -> {
				Optional<StockTimeline> resp = stockTimelineRepository.findById(code + "-" + date);
				if (resp.isEmpty()) {
					log.debug("Fetching stock data for date {}", date);
					String url = UriComponentsBuilder.fromUriString(baseURL).queryParam("function", dailyHistoricalKey)
							.queryParam("symbol", code).queryParam("apikey", apiKey).build().toUriString();
					String response = this.restTemplate.getForObject(url, String.class);
					JSONObject jsonObject = new JSONObject(response);
					if (jsonObject != null && !jsonObject.has(APIConstants.ERROR)
							&& jsonObject.has(APIConstants.DAILY_TIMESERIES)
							&& jsonObject.getJSONObject(APIConstants.DAILY_TIMESERIES).has(date)) {

						StockTimeline stock = APIUtil.convertJSONToStockTimeline(
								jsonObject.getJSONObject(APIConstants.DAILY_TIMESERIES).getJSONObject(date), date,
								stockObj);
						historicalStocks.add(stock);
						stockTimelineRepository.save(stock);
					} else {
						log.debug("!!! No data found for {} and date {}", code, date);
					}
				} else {
					log.info("Querying the data from database for code {}", code);
					historicalStocks.add(resp.get());
				}
			});
		}
		return historicalStocks;
	}

	public Stocks checkIfStockIsValid(String code) {
		Optional<Stocks> resp = stocksRepository.findById(code);
		if (resp.isEmpty()) {
			String url = UriComponentsBuilder.fromUriString(baseURL).queryParam("function", searchKey)
					.queryParam("keywords", code).queryParam("apikey", apiKey).build().toUriString();
			String response = this.restTemplate.getForObject(url, String.class);
			JSONObject jsonObject = new JSONObject(response);
			if (jsonObject.has(APIConstants.BEST_MATCHES)
					&& jsonObject.getJSONArray(APIConstants.BEST_MATCHES).length() > 0) {
				JSONArray array = jsonObject.getJSONArray(APIConstants.BEST_MATCHES);
				for (int i = 0; i < array.length(); i++) {
					Stocks stock = APIUtil.convertJSONToStock((JSONObject) array.get(i));
					if (stock != null) {
						stocksRepository.save(stock);
						return stock;
					}
				}
			}
			return null;
		} else {
			return resp.get();
		}
	}

	public TransferObject fetchAverageData(List<StockTimeline> list) {
		TransferObject data = new TransferObject();
		Double sum = 0d;
		Double minimum = list.get(0).getLow();
		Double maximum = list.get(0).getHigh();
		for (StockTimeline obj : list) {
			data.setCode(obj.getCode());
			data.setName(obj.getName());
			minimum = (minimum > obj.getLow()) ? obj.getLow() : minimum;
			maximum = (maximum > obj.getHigh()) ? obj.getHigh() : maximum;
			sum = sum + obj.getClose();
		}
		data.setMinimum(minimum);
		data.setMaximum(maximum);
		data.setAverage(sum / list.size());
		return data;
	}

}
