import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListsubredditsComponent } from './listsubreddits.component';

describe('ListsubredditsComponent', () => {
  let component: ListsubredditsComponent;
  let fixture: ComponentFixture<ListsubredditsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListsubredditsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListsubredditsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
