import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPackComponent } from './edit-pack.component';

describe('EditPackComponent', () => {
  let component: EditPackComponent;
  let fixture: ComponentFixture<EditPackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditPackComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditPackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
