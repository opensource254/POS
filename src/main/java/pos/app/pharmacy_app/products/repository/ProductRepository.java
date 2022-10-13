package pos.app.pharmacy_app.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.products.entity.Products;

public interface ProductRepository extends JpaRepository<Products,Long> {
    Products findById(Products product);
}
