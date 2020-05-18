import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashUsuariosComponent } from './dash-usuarios.component';

describe('UsuariosComponent', () => {
  let component: DashUsuariosComponent;
  let fixture: ComponentFixture<DashUsuariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashUsuariosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashUsuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
