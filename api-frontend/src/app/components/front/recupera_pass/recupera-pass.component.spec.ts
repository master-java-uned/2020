/**
 * Peter Fight
 */

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecuperaPassComponent } from './recupera-pass.component';

describe('RecuperaPassComponent', () => {
  let component: RecuperaPassComponent;
  let fixture: ComponentFixture<RecuperaPassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecuperaPassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecuperaPassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
