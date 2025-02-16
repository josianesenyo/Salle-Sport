package projet.com.salleSport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.com.salleSport.models.Pack;

public interface PackRepository extends JpaRepository<Pack, Long> {
    List<Pack> findByOfferName(String offerName);
    List<Pack> findByDurationMonths(int durationMonths);
    List<Pack> findByMonthlyPrice(double monthlyPrice);
}
