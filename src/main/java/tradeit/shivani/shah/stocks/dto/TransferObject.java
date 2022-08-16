package tradeit.shivani.shah.stocks.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferObject {

	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("date")
	private LocalDate date;
	@JsonProperty("listOfStocks")
	private List<Stock> listOfStocks;
	@JsonProperty("average")
	private Double average;
	@JsonProperty("minimum")
	private Double minimum;
	@JsonProperty("maximum")
	private Double maximum;
	@JsonProperty("statusDesc")
	private String statusDesc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Stock> getListOfStocks() {
		return listOfStocks;
	}

	public void setListOfStocks(List<Stock> listOfStocks) {
		this.listOfStocks = listOfStocks;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public Double getMinimum() {
		return minimum;
	}

	public void setMinimum(Double minimum) {
		this.minimum = minimum;
	}

	public Double getMaximum() {
		return maximum;
	}

	public void setMaximum(Double maximum) {
		this.maximum = maximum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}