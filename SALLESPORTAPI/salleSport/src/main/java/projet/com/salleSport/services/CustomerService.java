package projet.com.salleSport.services;

import projet.com.salleSport.dto.CustomerDto;
import projet.com.salleSport.exception.ResourceNotFoundException;
import projet.com.salleSport.models.Customer;
import projet.com.salleSport.models.Subscription;
import projet.com.salleSport.repositories.CustomerRepository;
import projet.com.salleSport.repositories.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Customer createCustomer(Customer customer) {
        // Vérifier si un client avec le même numéro de téléphone existe déjà
        Optional<Customer> existingCustomer = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("Un client avec le même numéro de téléphone existe déjà.");
        }

        // Créer le nouveau client
        customer.setRegistrationDate(LocalDate.now());
        return customerRepository.save(customer);
    }

    public List<Subscription> getActiveSubscriptions(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return subscriptionRepository.findActiveSubscriptionsByCustomerId(customer.getId());
        } else {
            return List.of();
        }
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerDto::new).toList();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'ID : " + id));
    }

    // public List<Customer> searchCustomersByLastName(String lastName) {
    //     return customerRepository.findByLastName(lastName);
    // }

    public List<Customer> searchCustomers(Long id, String firstName, String lastName) {
        if (id != null) {
            Optional<Customer> customerById = customerRepository.findById(id);
            return customerById.isPresent() ? List.of(customerById.get()) : List.of();
        } else if (firstName != null && lastName != null) {
            return customerRepository.findByLastNameAndFirstName(firstName, lastName);
        } else if (firstName != null) {
            return customerRepository.findByFirstName(firstName);
        } else if (lastName != null) {
            return customerRepository.findByLastName(lastName);
        } else {
            return List.of();
        }
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        existingCustomer.setActiveSubscription(updatedCustomer.isActiveSubscription());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer existingCustomer = getCustomerById(id);
        customerRepository.delete(existingCustomer);
    }
}
