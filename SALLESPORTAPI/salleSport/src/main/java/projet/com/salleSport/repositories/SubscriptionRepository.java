package projet.com.salleSport.repositories;


import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projet.com.salleSport.dto.SubscriptionDto;
import projet.com.salleSport.models.Subscription;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByCustomerId(Long customerId);

    @Query("SELECT SUM(p.monthlyPrice) FROM Subscription s JOIN s.pack p WHERE s.customer.activeSubscription = true")
    Double calculateEstimatedRevenue();

    @Query("SELECT s FROM Subscription s WHERE s.startDate BETWEEN :startDate AND :endDate")
    List<Subscription> findSubscriptionsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM Subscription s WHERE s.customer.activeSubscription = true")
    List<Subscription> findActiveSubscriptions();

    // find active subscriptions by customer id
    @Query("SELECT s FROM Subscription s WHERE s.customer.id = :customerId AND s.customer.activeSubscription = true")
    List<SubscriptionDto> findActiveSubscriptionByCustomerId(@Param("customerId") Long customerId);
    
    @Query("SELECT s FROM Subscription s WHERE s.customer.id = :customerId AND s.customer.activeSubscription = true")
    List<Subscription> findActiveSubscriptionsByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT s FROM Subscription s WHERE s.customer.id = :customerId AND s.customer.activeSubscription = true")
    List<Subscription> findActiveByCustomerId(@Param("customerId") Long customerId);

    // @Query("SELECT c FROM Customer c WHERE c.id = :customerId AND c.activeSubscription = true")
    // List<Subscription> findByCustomerIdAndActiveSubscription(@Param("customerId") Long customerId);
    
}
