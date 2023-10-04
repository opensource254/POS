package pos.app.pharmacy_app.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.billing.entity.BillingAddress;

import java.util.Optional;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, String> {
    Optional<BillingAddress>findByUserId(String Id);
    Boolean existsByUserId(String Id);
}
