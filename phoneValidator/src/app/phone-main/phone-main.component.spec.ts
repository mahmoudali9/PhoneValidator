import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhoneMainComponent } from './phone-main.component';

describe('PhoneMainComponent', () => {
  let component: PhoneMainComponent;
  let fixture: ComponentFixture<PhoneMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhoneMainComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PhoneMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
