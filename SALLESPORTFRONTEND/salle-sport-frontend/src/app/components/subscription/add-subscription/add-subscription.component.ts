import { Component } from '@angular/core';
import { Customer } from '../../../dto/customer.model';
import { Pack } from '../../../dto/pack.model';
import { SubscriptionService } from '../../../services/subscription.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../../services/customer.service';
import { PackService } from '../../../services/pack.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-subscription',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-subscription.component.html',
  styleUrl: './add-subscription.component.css'
})
export class AddSubscriptionComponent {
  customers: Customer[] = [];
  packs: Pack[] = [];
  selectedCustomerId: number = 0;
  selectedPackId: number = 0;
  errorMessage: string = '';

  constructor(private subscriptionService: SubscriptionService,
    private customerService: CustomerService,
    private packService: PackService,
    private router: Router
  ) {}

  ngOnInit() {
    this.customerService.getCustomers().subscribe(
      (data) => {
        this.customers = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des clients :', error);
      }
    );

    this.packService.getPacks().subscribe(
      (data) => {
        this.packs = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des offres :', error);
      }
    );
  }

  goBack() {
    this.router.navigate(['/subscription-list']);
  }
  

  onSubscribe() {
    if (this.selectedCustomerId && this.selectedPackId) {
      this.subscriptionService.subscribeCustomer(this.selectedCustomerId, this.selectedPackId).subscribe(
        () => {
          alert('Souscription réussie !');
          // Redirection vers la liste des abonnements ou fermeture du formulaire
        },
        (error) => {
          console.error('Erreur lors de la souscription :', error);
          this.errorMessage = 'Ce client est déjà un abonné.';
        }
      );
    } else {
      this.errorMessage = 'Veuillez sélectionner un client et une offre.';
    }
  }
}
