package tradeit.shivani.shah.stocks.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stocks {

	@Id
	private String code;
	private String name;
	private String region;
	private String currency;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
