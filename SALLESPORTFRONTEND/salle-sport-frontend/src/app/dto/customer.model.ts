import { Subscription } from "./subscription.model";

export interface Customer {
    id: number;
    lastName: string;
    firstName: string;
    registrationDate: string;
    phoneNumber: string;
    activeSubscription: boolean;
    subscription?: Subscription;
}
