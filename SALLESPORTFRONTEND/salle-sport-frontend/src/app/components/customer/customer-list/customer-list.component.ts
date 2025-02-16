import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Customer } from '../../../dto/customer.model';
import { CustomerService } from '../../../services/customer.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@Component({
  selector: 'app-customer-list',
  imports: [CommonModule, FormsModule],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class CustomerListComponent implements OnInit{
  customers: Customer[] = [];
  searchTerm: string = '';
  searchType: string = '';

  foundCustomers: Customer[] = [];
  customerToEdit: Customer | undefined;
  customerToView = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    registrationDate: '',
    activeSubscription: false
  }
  isEditing: boolean = false;
  isViewing: boolean = false;
  searchId: number = 0;
  searchFirstName: string = '';
  searchLastName: string = '';


  constructor(
    private customerService: CustomerService,
    private authService: AuthService,
    private router: Router) {}

  ngOnInit() {
    this.customerService.getCustomers().subscribe(
      (data) => {
        this.customers = data;
        console.log(this.customers);
      },
      (error) => {
        console.error('Erreur lors de la récupération des clients :', error);
      }
    );
  }

  // ngOnInit() {
  //   this.customerService.getCustomers().subscribe(data=> {
  //     this.customers = data;
  //   });
  // }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  
  // onSearch() {
  //   if (this.searchTerm.trim() !== '') {
  //     this.customerService.searchCustomers(this.searchTerm).subscribe(
  //       (data) => {
  //         this.foundCustomers = data;
  //       },
  //       (error) => {
  //         console.error('Erreur lors de la recherche des clients', error);
  //       }
  //     );
  //   } else {
  //     this.foundCustomers = [];
  //   }
  // }

  onSearch() {
    let id: number = 0;
    let firstName: string = '';
    let lastName: string = '';

    if (this.searchType === 'id') {
      id = parseInt(this.searchTerm);
    } else if (this.searchType === 'firstName') {
      firstName = this.searchTerm;
    } else if (this.searchType === 'lastName') {
      lastName = this.searchTerm;
    }
    this.searchCustomers(id, firstName, lastName);
  }

  searchCustomers(id?: number, firstName?: string, lastName?: string) {
    this.customerService.searchCustomers(id, firstName, lastName).subscribe(
      (data) => {
        this.foundCustomers = data;
      },
      (error) => {
        console.error('Erreur lors de la recherche des clients :', error);
      }
    );
  }

  onDelete(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce client ?')) {
      this.customerService.deleteCustomer(id).subscribe(
        () => {
          alert('Client supprimé avec succès !');
          this.customers = this.customers.filter(c => c.id !== id);
        },
        (error) => {
          console.error('Erreur lors de la suppression du client :', error);
        }
      );
    }
  }

  startEdit(id: number) {
    this.router.navigate(['/edit-customer', id]);
  }

  startView(id: number) {
    this.router.navigate(['/customers', id]);
  }

  closeEdit() {
    this.customerToEdit = undefined;
    this.isEditing = false;
  }

}
