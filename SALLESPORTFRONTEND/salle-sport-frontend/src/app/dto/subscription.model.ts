import { Customer } from "./customer.model";
import { Pack } from "./pack.model";

export interface Subscription {
    id: number;
    pack: Pack;
    startDate: string;
    customer: Customer;
}
