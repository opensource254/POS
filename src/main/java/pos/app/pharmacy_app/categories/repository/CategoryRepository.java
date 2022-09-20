package pos.app.pharmacy_app.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.categories.entity.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
