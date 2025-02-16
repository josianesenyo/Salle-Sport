package projet.com.salleSport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le champ nom de l'offre ne peut pas être vide")
    @Column(name = "offer_name")
    private String offerName;

    @NotNull(message = "Le champ description ne peut pas être vide")
    @Column(name = "duration_months")
    private int durationMonths;

    @NotNull(message = "Le champ prix mensuel ne peut pas être vide")
    @Column(name = "monthly_price")
    private double monthlyPrice;
}
