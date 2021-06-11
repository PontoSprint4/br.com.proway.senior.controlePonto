import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioCriarTurnoComponent } from './formulario-criar-turno.component';

describe('FormularioCriarTurnoComponent', () => {
  let component: FormularioCriarTurnoComponent;
  let fixture: ComponentFixture<FormularioCriarTurnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioCriarTurnoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioCriarTurnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
