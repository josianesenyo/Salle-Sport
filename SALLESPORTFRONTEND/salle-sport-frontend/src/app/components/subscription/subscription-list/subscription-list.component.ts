import { Component, OnInit } from '@angular/core';
import { Subscription } from '../../../dto/subscription.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SubscriptionService } from '../../../services/subscription.service';
import { CustomerService } from '../../../services/customer.service';
import { Customer } from '../../../dto/customer.model';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-subscription-list',
  imports: [FormsModule, CommonModule],
  templateUrl: './subscription-list.component.html',
  styleUrl: './subscription-list.component.css'
})

export class SubscriptionListComponent implements OnInit {
  subscriptions: Subscription[] = [];
  customers: Customer[] = [];
  // newSubscription: { customerId: number; packId: number } = { customerId: 0, packId: 0 };
  subscriptionErrorMessage: string = '';

  constructor(
    private subscriptionService: SubscriptionService,
    private customerService: CustomerService,
    private router: Router,
    private authService: AuthService
  ) {}
  
  ngOnInit() {
    this.loadSubscriptions();
  }

  loadSubscriptions() {
    this.subscriptionService.getAllSubscriptions().subscribe(
      (data) => {
        this.subscriptions = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des abonnements :', error);
      }
    );
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  onCancelSubscription(subscriptionId: number) {
    if (confirm('Êtes-vous sûr de vouloir résilier cet abonnement ?')) {
      this.subscriptionService.cancelSubscription(subscriptionId).subscribe(
        () => {
          alert('Abonnement résilié avec succès !');
          this.subscriptions = this.subscriptions.filter(s => s.id !== subscriptionId);
          console.log(this.subscriptions);
          this.loadSubscriptions();
          // this.router.navigate(['/subscription-list']);
        }
      );
    }
  }
}
