import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JornadaCrudListagemAdmComponent } from './jornada-crud-listagem-adm.component';

describe('JornadaCrudListagemAdmComponent', () => {
  let component: JornadaCrudListagemAdmComponent;
  let fixture: ComponentFixture<JornadaCrudListagemAdmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JornadaCrudListagemAdmComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JornadaCrudListagemAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
