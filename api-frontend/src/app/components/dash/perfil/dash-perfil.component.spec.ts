import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashPerfilComponent } from './dash-perfil.component';

describe('PerfilComponent', () => {
  let component: DashPerfilComponent;
  let fixture: ComponentFixture<DashPerfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashPerfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashPerfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
