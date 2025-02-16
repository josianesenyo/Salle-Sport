import { Component } from '@angular/core';
import { Customer } from '../../../dto/customer.model';
import { CustomerService } from '../../../services/customer.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerDto } from '../../../dto/customerDto.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-customer',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-customer.component.html',
  styleUrl: './add-customer.component.css'
})
export class AddCustomerComponent {
  newCustomer: CustomerDto = {
    lastName: '',
    firstName: '',
    registrationDate: new Date().toISOString(),
    phoneNumber: '',
  };

  errorMessage: string = '';

  constructor(
    private customerService: CustomerService,
    private router: Router) {}

  onAddCustomer() {
    this.customerService.createCustomerDto(this.newCustomer).subscribe(
      () => {
        alert('Client ajouté avec succès');
        this.newCustomer = {
          lastName: '',
          firstName: '',
          registrationDate: new Date().toISOString(),
          phoneNumber: '',
        };
        this.errorMessage = '';
        this.router.navigate(['/customers']);

      },
      (error) => {
        console.error('Erreur lors de l\'ajout du client', error);
        if (error.status === 400) {
          this.errorMessage = 'Un client avec le même numéro de téléphone existe déjà.';
        } else {
          this.errorMessage = 'Erreur lors de l\'ajout du client.';
        }
      }
    );
  }
}
