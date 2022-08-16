package tradeit.shivani.shah.stocks.util;

import org.json.JSONObject;

import tradeit.shivani.shah.stocks.model.StockTimeline;
import tradeit.shivani.shah.stocks.model.Stocks;

public class APIUtil {

	public static StockTimeline convertJSONToStockTimeline(JSONObject json, String date, Stocks stock) {

		StockTimeline stockTimeline = new StockTimeline();
		if (json.has(APIConstants.CLOSE)) {
			stockTimeline.setClose(Double.valueOf(json.getString(APIConstants.CLOSE)));
		}
		if (json.has(APIConstants.HIGH)) {
			stockTimeline.setHigh(Double.valueOf(json.getString(APIConstants.HIGH)));
		}
		if (json.has(APIConstants.OPEN)) {
			stockTimeline.setOpen(Double.valueOf(json.getString(APIConstants.OPEN)));
		}
		if (json.has(APIConstants.LOW)) {
			stockTimeline.setLow(Double.valueOf(json.getString(APIConstants.LOW)));
		}
		if (json.has(APIConstants.VOLUME)) {
			stockTimeline.setVolume(Double.valueOf(json.getString(APIConstants.VOLUME)));
		}
		stockTimeline.setDate(DateUtil.parseDate(date));
		stockTimeline.setCode(stock.getCode());
		stockTimeline.setName(stock.getName());
		stockTimeline.setDate(DateUtil.parseDate(date));
		stockTimeline.setId(stock.getCode() + "-" + stockTimeline.getDate());

		return stockTimeline;
	}

	public static Stocks convertJSONToStock(JSONObject json) {
		if (json.has(APIConstants.MATCH_SCORE)
				&& APIConstants.PERFECT_MATCH.equals(json.getString(APIConstants.MATCH_SCORE))) {
			Stocks stocks = new Stocks();
			if (json.has(APIConstants.SYMBOL)) {
				stocks.setCode(json.getString(APIConstants.SYMBOL));
			}
			if (json.has(APIConstants.REGION)) {
				stocks.setRegion(json.getString(APIConstants.REGION));
			}
			if (json.has(APIConstants.CURRENCY)) {
				stocks.setCurrency(json.getString(APIConstants.CURRENCY));
			}
			if (json.has(APIConstants.NAME)) {
				stocks.setName(json.getString(APIConstants.NAME));
			}
			return stocks;
		}
		return null;
	}

}