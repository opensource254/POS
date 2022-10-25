package pos.app.pharmacy_app.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.app.pharmacy_app.users.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findUsersByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
