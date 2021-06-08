import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PontoCrudListagemComponent } from './ponto-crud-listagem.component';

describe('PontoCrudListagemComponent', () => {
  let component: PontoCrudListagemComponent;
  let fixture: ComponentFixture<PontoCrudListagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PontoCrudListagemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PontoCrudListagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
