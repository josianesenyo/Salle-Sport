package projet.com.salleSport.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
// import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "phone_number")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le champ nom ne peut pas être vide")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Le champ prenom ne peut pas être vide")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Le champ date d'enregistrement ne peut pas être vide")
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @NotNull(message = "Le champ numero de téléphone ne peut pas être vide")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "active_subscription")
    private boolean activeSubscription;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscription;

    public boolean isActive() {
        return activeSubscription && LocalDate.now().isBefore(calculateEndDate());
    }

    public LocalDate calculateEndDate() {
        if (subscription != null && !subscription.isEmpty()) {
            return subscription.get(0).getStartDate().plusMonths(subscription.get(0).getPack().getDurationMonths());
        }
        return null;
    }

    // public LocalDate calculateEndDate() {
    //     return startDate.plusMonths(pack.getDurationInMonths());
    // }
}
