import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PontoMarcarComponent } from './ponto-marcar.component';

describe('PontoMarcarComponent', () => {
  let component: PontoMarcarComponent;
  let fixture: ComponentFixture<PontoMarcarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PontoMarcarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PontoMarcarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
