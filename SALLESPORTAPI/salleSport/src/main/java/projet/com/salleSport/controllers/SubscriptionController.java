package projet.com.salleSport.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projet.com.salleSport.dto.SubscriptionDto;
import projet.com.salleSport.models.Subscription;
import projet.com.salleSport.services.SubscriptionService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // Souscrire un client à une offre
    @PostMapping("/subscribe")
    public ResponseEntity<SubscriptionDto> subscribeCustomer(@RequestParam Long customerId, @RequestParam Long packId) {
        try {
            Subscription subscription = subscriptionService.subscribeCustomer(customerId, packId);
            SubscriptionDto subscriptionDto = new SubscriptionDto(subscription);
            return new ResponseEntity<>(subscriptionDto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Subscription> getSubscriptionByCustomerId(@PathVariable Long customerId) {
        Subscription subscription = subscriptionService.getSubscriptionByCustomerId(customerId);
        if (subscription == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subscription);
    }
    
    @GetMapping("/{customerId}/active")
    public ResponseEntity<List<SubscriptionDto>> getActiveSubscriptionByCustomerId(@PathVariable Long customerId) {
        List<SubscriptionDto> subscription = subscriptionService.getActiveSubscriptionByCustomerId(customerId);
        if (subscription == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subscription);
    }
    
    // Résilier un abonnement
    @DeleteMapping("/cancel/{subscriptionId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getSubscriptions() {
        List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }
}
