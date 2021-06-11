import { TestBed } from '@angular/core/testing';

import { JornadaService } from './jornada.service';

describe('JornadaService', () => {
  let service: JornadaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JornadaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
