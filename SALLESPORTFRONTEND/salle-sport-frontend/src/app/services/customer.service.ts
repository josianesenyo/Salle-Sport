import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../dto/customer.model';
import { Observable, Subscription } from 'rxjs';
import { CustomerDto } from '../dto/customerDto.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/api/customers';

  constructor(private http: HttpClient) {}

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer);
  }
  
  createCustomerDto(customerDto: CustomerDto): Observable<CustomerDto> {
    return this.http.post<CustomerDto>(this.apiUrl, customerDto);
  }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }

  getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${id}`);
  }

  getActiveSubscriptions(customerId: number): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.apiUrl}/${customerId}/subscriptions`);
  }

  // searchCustomersByLastName(lastName: string, firstName: string): Observable<Customer[]> {
  //   return this.http.get<Customer[]>(`${this.apiUrl}/search?lastName=${lastName}`);
  // }

  searchCustomers(id?: number, firstName?: string, lastName?: string): Observable<Customer[]> {
    let params = new HttpParams();

    if (id !== undefined) {
      params = params.append('id', id.toString());
    }
    if (firstName) {
      params = params.append('firstName', firstName);
    }
    if (lastName) {
      params = params.append('lastName', lastName);
    }

    return this.http.get<Customer[]>(`${this.apiUrl}/search`, { params });
  }

  updateCustomer(id: number, customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.apiUrl}/${id}`, customer);
  }

  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
