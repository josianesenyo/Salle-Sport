package projet.com.salleSport.controllers;

// import java.time.LocalDate;
import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projet.com.salleSport.dto.CustomerDto;
// import projet.com.salleSport.exception.ResourceNotFoundException;
import projet.com.salleSport.models.Customer;
import projet.com.salleSport.models.Subscription;
// import projet.com.salleSport.repositories.CustomerRepository;
import projet.com.salleSport.services.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    // private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // @PostMapping
    // public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
    //     customer.setRegistrationDate(LocalDate.now());
    //     Customer savedCustomer = customerRepository.save(customer);
    //     return ResponseEntity.ok(savedCustomer);
    // }
    
    
    @GetMapping
    public ResponseEntity<List<CustomerDto>> listCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByLastName(@PathVariable Long id) {
    return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/{id}/active-subscriptions")
    public ResponseEntity<List<Subscription>> getActiveSubscriptions(@PathVariable Long id) {
        List<Subscription> activeSubscriptions = customerService.getActiveSubscriptions(id);
        return ResponseEntity.ok(activeSubscriptions);
    }


    // @GetMapping("/search")
    // public ResponseEntity<List<Customer>> searchCustomerByLastName(@RequestParam String lastName){
    //     List<Customer> customers = customerRepository.findByLastName(lastName);
    //     if(customers.isEmpty()){
    //         throw new ResourceNotFoundException("Aucun client trouvé avec le nom de famille : " + lastName);
    //     }
    //     return ResponseEntity.ok(customers);
    // }

    @GetMapping("/search")
    public List<Customer> searchCustomers(
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName
    ) {
        try {
            return customerService.searchCustomers(id, firstName, lastName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche des clients", e);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
