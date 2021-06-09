import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JornadaCriarComponent } from './jornada-criar.component';

describe('JornadaCriarComponent', () => {
  let component: JornadaCriarComponent;
  let fixture: ComponentFixture<JornadaCriarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JornadaCriarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JornadaCriarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
