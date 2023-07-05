package pos.app.pharmacy_app.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.orders.entity.Cart;
import pos.app.pharmacy_app.orders.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface CartRepositories extends JpaRepository<Cart, Long> {
    Optional<Cart>findFirstByCustomerDetailsUserLoginAAndStatusOrderByCartIdAsc(String login, OrderStatus orderStatus);
    List<Cart> findAllByCustomerDetailsUserLoginAndStatusNot(String user, OrderStatus orderStatus);

    Optional<Cart> findOneByPaymentModificationReference(String paymentRef);

    Optional<Cart> findOneByPaymentReference(String paymentRef);
}
