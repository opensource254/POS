package pos.app.pharmacy_app.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.stock.entity.Stocks;

public interface StockRepository extends JpaRepository<Stocks, Long> {
}
