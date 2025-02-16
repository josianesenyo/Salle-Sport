import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { AddCustomerComponent } from './components/customer/add-customer/add-customer.component';
import { AddPackComponent } from './components/pack/add-pack/add-pack.component';
import { PackListComponent } from './components/pack/pack-list/pack-list.component';
import { SubscriptionListComponent } from './components/subscription/subscription-list/subscription-list.component';
import { EditCustomerComponent } from './components/customer/edit-customer/edit-customer.component';
import { ViewCustomerComponent } from './components/customer/view-customer/view-customer.component';
import { EditPackComponent } from './components/pack/edit-pack/edit-pack.component';
import { ViewPackComponent } from './components/pack/view-pack/view-pack.component';
import { StatisticsComponent } from './components/statistic/statistics/statistics.component';
import { AddSubscriptionComponent } from './components/subscription/add-subscription/add-subscription.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'customers', component: CustomerListComponent, canActivate: [AuthGuard] },
    { path: 'customers', component: CustomerListComponent, canActivate: [AuthGuard] },
    { path: 'add-customer', component: AddCustomerComponent, canActivate: [AuthGuard] },
    { path: 'packs', component: PackListComponent, canActivate: [AuthGuard] },
    { path: 'add-pack', component: AddPackComponent, canActivate: [AuthGuard] },
    { path: 'subscription-list', component: SubscriptionListComponent, canActivate: [AuthGuard] },
    { path: 'edit-customer/:id', component: EditCustomerComponent, canActivate: [AuthGuard] },
    { path: 'customers/:id', component: ViewCustomerComponent, canActivate: [AuthGuard] },
    { path: 'edit-pack/:id', component: EditPackComponent, canActivate: [AuthGuard] },
    { path: 'view-pack/:id', component: ViewPackComponent, canActivate: [AuthGuard] },
    { path: 'statistics', component: StatisticsComponent, canActivate: [AuthGuard] },
    { path: 'add-subscription', component: AddSubscriptionComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: '**', redirectTo: '/login' },
];
