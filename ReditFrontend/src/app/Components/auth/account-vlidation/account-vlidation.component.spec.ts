import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountVlidationComponent } from './account-vlidation.component';

describe('AccountVlidationComponent', () => {
  let component: AccountVlidationComponent;
  let fixture: ComponentFixture<AccountVlidationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountVlidationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountVlidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
