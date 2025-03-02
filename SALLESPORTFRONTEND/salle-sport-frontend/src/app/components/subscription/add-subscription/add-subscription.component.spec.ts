import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSubscriptionComponent } from './add-subscription.component';

describe('AddSubscriptionComponent', () => {
  let component: AddSubscriptionComponent;
  let fixture: ComponentFixture<AddSubscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddSubscriptionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSubscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
