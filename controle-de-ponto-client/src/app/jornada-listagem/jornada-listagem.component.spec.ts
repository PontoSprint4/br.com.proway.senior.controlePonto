import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JornadaListagemComponent } from './jornada-listagem.component';

describe('JornadaListagemComponent', () => {
  let component: JornadaListagemComponent;
  let fixture: ComponentFixture<JornadaListagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JornadaListagemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JornadaListagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
