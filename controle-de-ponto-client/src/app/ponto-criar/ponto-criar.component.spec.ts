import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PontoCriarComponent } from './ponto-criar.component';

describe('PontoCriarComponent', () => {
  let component: PontoCriarComponent;
  let fixture: ComponentFixture<PontoCriarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PontoCriarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PontoCriarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
