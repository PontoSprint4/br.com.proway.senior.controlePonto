import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JornadaEditarComponent } from './jornada-editar.component';

describe('JornadaEditarComponent', () => {
  let component: JornadaEditarComponent;
  let fixture: ComponentFixture<JornadaEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JornadaEditarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JornadaEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
