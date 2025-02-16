import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SubscriptionService } from '../../../services/subscription.service';
import { Subscription } from '../../../dto/subscription.model';

@Component({
  selector: 'app-statistics',
  imports: [CommonModule],
  templateUrl: './statistics.component.html',
  styleUrl: './statistics.component.css'
})
export class StatisticsComponent {
  activeCustomersCount: number = 0;
  estimatedMonthlyRevenue: number = 0;
  subscriptions: Subscription[] = [];

  constructor(private subscriptionService: SubscriptionService){}

  ngOnInit() {
    this.subscriptionService.getAllSubscriptions().subscribe(
      (data) => {
        this.subscriptions = data;
        console.log('Abonnements récupérés :', this.subscriptions);
        this.calculateStatistics();
      },
      (error) => {
        console.error('Erreur lors de la récupération des abonnements :', error);
      }
    );
  }

  calculateStatistics() {
    console.log('Calcul des statistiques');
    const activeCustomers = this.subscriptions.filter(subscription => subscription.customer?.activeSubscription);
    console.log('Clients actifs :', activeCustomers);

    this.activeCustomersCount = activeCustomers.length;

    this.estimatedMonthlyRevenue = activeCustomers.reduce((total, subscription) => {
      if (subscription.pack?.monthlyPrice) {
        return total + subscription.pack.monthlyPrice;
      }
      return total;
    }, 0);

    console.log('Nombre total de clients actifs :', this.activeCustomersCount);
    console.log('Chiffre d\'affaires mensuel estimé :', this.estimatedMonthlyRevenue);
  }


  exportSubscriptions(startDate: string, endDate: string) {
    // Implémentez la logique pour exporter les informations sur les abonnements sur une période donnée
    console.log('Exporter les informations sur les abonnements du', startDate, 'au', endDate);
  }
}