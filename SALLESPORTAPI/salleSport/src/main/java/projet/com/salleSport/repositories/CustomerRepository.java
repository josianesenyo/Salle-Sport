package projet.com.salleSport.repositories;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.com.salleSport.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstName(String firstName);
    Optional<Customer> findByRegistrationDate(LocalDate registrationDate);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    Optional<Customer> findByActiveSubscription(boolean activeSubscription);
    List<Customer> findByLastNameAndFirstName(String lastName, String firstName);
    long countByActiveSubscription(boolean activeSubscription);
}
