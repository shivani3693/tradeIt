package tradeit.shivani.shah.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tradeit.shivani.shah.stocks.model.StockTimeline;

public interface CurrencyRepository extends JpaRepository<StockTimeline, String> {

}
