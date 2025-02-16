package projet.com.salleSport.services;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projet.com.salleSport.repositories.CustomerRepository;
import projet.com.salleSport.repositories.SubscriptionRepository;
import projet.com.salleSport.models.Subscription;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;
    
    public long getActiveCustomersCount() {
        return customerRepository.countByActiveSubscription(true);
    }

    public Double getEstimatedMonthlyRevenue() {
        return subscriptionRepository.calculateEstimatedRevenue();
    }

    public List<Subscription> getSubscriptionsBetweenDates(LocalDate start, LocalDate end) {
        return subscriptionRepository.findSubscriptionsBetweenDates(start, end);
    }
}
