package pos.app.pharmacy_app.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.orders.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
