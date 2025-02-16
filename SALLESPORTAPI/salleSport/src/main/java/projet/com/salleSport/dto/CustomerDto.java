package projet.com.salleSport.dto;

// import java.util.List;
// import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import projet.com.salleSport.models.Customer;

@Data
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
    this.firstName = customer.getFirstName();
    this.lastName = customer.getLastName();
    this.phoneNumber = customer.getPhoneNumber();
}
}
