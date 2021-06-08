import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TurnoDetailsComponent } from './turno-details.component';

describe('TurnoDetailsComponent', () => {
  let component: TurnoDetailsComponent;
  let fixture: ComponentFixture<TurnoDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TurnoDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TurnoDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
