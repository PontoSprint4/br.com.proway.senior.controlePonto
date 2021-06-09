import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PontoEditarComponent } from './ponto-editar.component';

describe('PontoEditarComponent', () => {
  let component: PontoEditarComponent;
  let fixture: ComponentFixture<PontoEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PontoEditarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PontoEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
