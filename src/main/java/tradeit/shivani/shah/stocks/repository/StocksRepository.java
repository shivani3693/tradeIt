package tradeit.shivani.shah.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tradeit.shivani.shah.stocks.model.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, String> {

}
