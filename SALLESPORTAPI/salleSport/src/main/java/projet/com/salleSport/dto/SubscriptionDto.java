package projet.com.salleSport.dto;

import java.time.LocalDate;
// import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
// import projet.com.salleSport.models.Customer;
import projet.com.salleSport.models.Pack;
import projet.com.salleSport.models.Subscription;

@Data
@NoArgsConstructor
public class SubscriptionDto {

    private Long id;
    private CustomerDto customer;
    private Pack pack;
    private LocalDate startDate;

    //Convert Subscription to SubscriptionDto
    public SubscriptionDto(Subscription subscription) {
        this.id = subscription.getId();
        this.customer = subscription.getCustomer() != null ? new CustomerDto(subscription.getCustomer()) : null;
        this.pack = subscription.getPack();
        this.startDate = subscription.getStartDate();
    }
}
