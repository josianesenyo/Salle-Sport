package projet.com.salleSport.models;

import java.time.LocalDate;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    // @JsonIgnore
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

    @NotNull
    @Column (name = "start_date")
    private LocalDate startDate;

}
