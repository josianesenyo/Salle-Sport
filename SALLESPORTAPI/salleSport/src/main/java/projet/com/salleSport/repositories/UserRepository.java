package projet.com.salleSport.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.com.salleSport.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByRole(String role);
}
