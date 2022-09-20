package pos.app.pharmacy_app.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.sales.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {

}
