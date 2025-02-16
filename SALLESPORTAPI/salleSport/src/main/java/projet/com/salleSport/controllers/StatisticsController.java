package projet.com.salleSport.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projet.com.salleSport.services.StatisticsService;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import projet.com.salleSport.models.Subscription;

import java.util.List;


@Controller
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/active-customers")
    public long getActiveCustomersCount() {
        return statisticsService.getActiveCustomersCount();
    }

    @GetMapping("/estimated-monthly-revenue")
    public Double getEstimatedMonthlyRevenue() {
        return statisticsService.getEstimatedMonthlyRevenue();
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportSubscriptions(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<Subscription> subscriptions = statisticsService.getSubscriptionsBetweenDates(startDate, endDate);
        
        // Génération du fichier CSV
        String fileContent = generateCSV(subscriptions);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=subscriptions.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(fileContent);
    }

    private String generateCSV(List<Subscription> subscriptions) {
        StringBuilder sb = new StringBuilder("ID,Client,Offre,Date de début\n");
        for (Subscription s : subscriptions) {
            sb.append(s.getId()).append(",")
              .append(s.getCustomer().getFirstName()).append(" ")
              .append(s.getCustomer().getLastName()).append(",")
              .append(s.getPack().getOfferName()).append(",")
              .append(s.getStartDate()).append("\n");
        }
        return sb.toString();
    }
}
