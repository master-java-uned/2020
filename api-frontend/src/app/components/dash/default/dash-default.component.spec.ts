import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashDefaultComponent } from './dash-default.component';

describe('DefaultComponent', () => {
  let component: DashDefaultComponent;
  let fixture: ComponentFixture<DashDefaultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashDefaultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashDefaultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
