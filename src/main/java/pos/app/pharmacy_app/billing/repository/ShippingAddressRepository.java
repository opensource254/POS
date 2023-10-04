package pos.app.pharmacy_app.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pos.app.pharmacy_app.billing.entity.ShippingAddress;

import java.util.Optional;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, String> {
    Optional<ShippingAddress>findByUserId(String Id);
    Boolean existsByUserId(String Id);
}
