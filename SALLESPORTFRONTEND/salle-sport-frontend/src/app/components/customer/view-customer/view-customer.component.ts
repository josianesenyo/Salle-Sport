import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../../../dto/customer.model';
import { CustomerService } from '../../../services/customer.service';
import { SubscriptionService } from '../../../services/subscription.service';
import { CommonModule, DatePipe } from '@angular/common';
import { Subscription } from '../../../dto/subscription.model';

@Component({
  selector: 'app-view-customer',
  imports: [DatePipe, CommonModule ],
  templateUrl: './view-customer.component.html',
  styleUrl: './view-customer.component.css'
})
export class ViewCustomerComponent implements OnInit{
  customer!: Customer;
  subscription: Subscription | undefined;
  activeSubscriptions: Subscription[] = [];

  constructor(
    private customerService: CustomerService,
    private subscriptionService: SubscriptionService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const customerId = this.route.snapshot.paramMap.get('id');
    if (customerId) {
      this.customerService.getCustomerById(+customerId).subscribe(
        (data) => {
          this.customer = data;
          if (this.customer && this.customer.activeSubscription) {
            this.subscription = this.customer.subscription;
            console.log('Abonnement récupéré :', this.subscription);
          }
        },
        (error) => {
          console.error('Erreur lors de la récupération du client :', error);
        }
      );
    } else {
      console.error('ID du client non trouvé dans les paramètres de la route.');
    }
  }

  getActiveSubscriptions(customerId: number) {
    this.subscriptionService.getActiveSubscriptions(customerId).subscribe(
      (data) => {
        this.activeSubscriptions = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des abonnements actifs :', error);
      }
    );
  }
}
