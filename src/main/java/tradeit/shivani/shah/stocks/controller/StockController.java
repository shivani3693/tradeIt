package tradeit.shivani.shah.stocks.controller;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import tradeit.shivani.shah.stocks.dto.TransferObject;
import tradeit.shivani.shah.stocks.service.StockService;

@RestController
@RequestMapping(value = "/api/stocks")
public class StockController {

	private Logger log = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService stockService;

	@ApiOperation(value = "This method is used to fetch information of all stocks")
	@GetMapping(value = "/fetch/{code}")
	public ResponseEntity<TransferObject> fetchAllStockInformation(@PathVariable String code) {
		log.debug("Method: fetchAllStockInformation");
		return ResponseEntity.ok().body(this.stockService.fetchAverageDataForStock(code));
	}
	

	@ApiOperation(value = "This method is used to download information of all stocks")
	@GetMapping(value = "/download/{code}")
	public ResponseEntity<Object> downloadAllStockInformation(@PathVariable String code) {
		log.debug("Method: fetchAllStockInformation");
		TransferObject obj = this.stockService.fetchAverageDataForStock(code);
		try {
			byte[] buf = new ObjectMapper().writeValueAsBytes(obj);
			return ResponseEntity
			        .ok()
			        .contentLength(buf.length)
			        .contentType(MediaType.parseMediaType("application/octet-stream"))
			        .header("Content-Disposition", "attachment; filename=\""+code+".json\"")
			        .body(new InputStreamResource(new ByteArrayInputStream(buf)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("Failed to pass json object for download, {}", e.getMessage());
			return ResponseEntity.internalServerError().body("Internal Error!");
		}
	}
}
