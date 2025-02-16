import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subscription } from '../dto/subscription.model';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private apiUrl = 'http://localhost:8080/api/subscriptions';

  constructor(private http: HttpClient) {}

  subscribeCustomer(customerId: number, packId: number): Observable<Subscription> {
    const params = { customerId, packId };
    return this.http.post<Subscription>(`${this.apiUrl}/subscribe`, {}, { params });
  }

  cancelSubscription(customerId: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/cancel/${customerId}`);
  }

  getSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(this.apiUrl);
  }

  getAllSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(this.apiUrl);
  }

  getSubscriptionByCustomerId(customerId: number): Observable<Subscription> {
    return this.http.get<Subscription>(`${this.apiUrl}/customer/${customerId}`);
  }

  getActiveSubscriptions(customerId: number): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.apiUrl}/${customerId}/active`)
      .pipe(map((response: any[]) => response.map((subscription: any) => {
        return {
          id: subscription.id,
          pack: subscription.pack,
          startDate: subscription.startDate,
          customer: subscription.customer
        };
      })));
  }
  
}
