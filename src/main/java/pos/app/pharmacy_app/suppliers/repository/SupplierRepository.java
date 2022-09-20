package pos.app.pharmacy_app.suppliers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.suppliers.entity.Suppliers;

public interface SupplierRepository extends JpaRepository<Suppliers,Long> {
}
