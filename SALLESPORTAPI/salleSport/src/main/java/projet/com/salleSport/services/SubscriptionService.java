package projet.com.salleSport.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import projet.com.salleSport.dto.SubscriptionDto;
import projet.com.salleSport.exception.ResourceNotFoundException;
import projet.com.salleSport.models.Customer;
import projet.com.salleSport.models.Pack;
import projet.com.salleSport.repositories.CustomerRepository;
import projet.com.salleSport.repositories.PackRepository;
import projet.com.salleSport.repositories.SubscriptionRepository;
import projet.com.salleSport.models.Subscription;

@Service
@RequiredArgsConstructor
public class SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final PackRepository packRepository;

    @Transactional
    public Subscription subscribeCustomer(Long customerId, Long packId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Pack> packOptional = packRepository.findById(packId);

        if (!customerOptional.isPresent() || !packOptional.isPresent()) {
            throw new RuntimeException("Client ou offre non trouvés.");
        }

        Customer customer = customerOptional.get();
        Pack pack = packOptional.get();

        // Vérifier si le client a déjà un abonnement actif
        if (customer.isActive()) {
            throw new RuntimeException("Le client a déjà un abonnement actif.");
        }

        // Créer la nouvelle souscription
        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setPack(pack);
        subscription.setStartDate(LocalDate.now());
        customer.setActiveSubscription(true);

        return subscriptionRepository.save(subscription);
    }

    @Transactional
    public void cancelSubscription(Long subscriptionId) {

         // Vérifier si le client existe
        // Customer customer = customerRepository.findById(customerId)
        // .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        // Vérifier si le client a un abonnement actif
        // if (customer.getSubscription() == null) {
        //     throw new RuntimeException("Aucun abonnement actif trouvé pour ce client.");
        // }

        // Récupérer l'abonnement
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
        .orElseThrow(() -> new RuntimeException("Abonnement non trouvé"));

        // Supprimer l'abonnement
        // subscription.setActiveSubscription(false);
        subscriptionRepository.delete(subscription);

        // Détacher l'abonnement du client
        // customer.setSubscription(null);
        // customer.setActiveSubscription(false);
        // customerRepository.save(customer); // IMPORTANT : Sauvegarder la mise à jour du client

    }

    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscription -> new SubscriptionDto(subscription)).toList();
    }

    public Subscription getSubscriptionByCustomerId(Long customerId) {
        // Implémentez la logique pour récupérer l'abonnement par ID de client
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findByCustomerId(customerId);
        return subscriptionOptional.orElse(null);
    }
    
    public List<SubscriptionDto> getActiveSubscriptionByCustomerId(Long customerId) {
        // Implémentez la logique pour récupérer l'abonnement par ID de client
        List<SubscriptionDto> subscriptionOptional = subscriptionRepository.findActiveSubscriptionByCustomerId(customerId);
        return subscriptionOptional;
    }

}
