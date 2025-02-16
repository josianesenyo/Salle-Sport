import { Component, Input } from '@angular/core';
import { Customer } from '../../../dto/customer.model';
import { CustomerService } from '../../../services/customer.service';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-customer',
  imports: [FormsModule],
  templateUrl: './edit-customer.component.html',
  styleUrl: './edit-customer.component.css'
})
export class EditCustomerComponent {
  customer: Customer = {
    id: 0,
    lastName: '',
    firstName: '',
    phoneNumber: '',
    registrationDate: '',
    activeSubscription: false
  };
  errorMessages!: string;

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const customerId = this.route.snapshot.paramMap.get('id');
    if (customerId) {
      this.customerService.getCustomerById(+customerId).subscribe(
        (data) => {
          this.customer = data;
        },
        (error) => {
          console.error('Erreur lors de la récupération du client :', error);
        }
      );
    } else {
      console.error('ID du client non trouvé dans les paramètres de la route.');
    }
  }
  onUpdateCustomer() {
    if (this.customer && this.customer.id !== undefined) {
      this.customerService.updateCustomer(this.customer.id, this.customer).subscribe(
        () => {
          alert('Client mis à jour avec succès !');
          this.router.navigate(['/customers']);
        },
        (error) => {
          this.errorMessages = 'Ce numero de téléphone existe déjà';
          console.error('Erreur lors de la mise à jour du client :', error);
        }
      );
    } else {
      console.error('ID du client non défini.');
    }
  }
}
