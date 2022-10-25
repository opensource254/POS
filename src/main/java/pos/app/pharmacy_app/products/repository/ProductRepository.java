package pos.app.pharmacy_app.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pos.app.pharmacy_app.products.entity.Products;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
 Optional<Products> findProductsByProductId(Long productId);

 String findProductsByProductId(String productsId);
}
