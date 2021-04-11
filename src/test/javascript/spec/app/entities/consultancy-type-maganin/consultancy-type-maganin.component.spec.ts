import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MaganinTestModule } from '../../../test.module';
import { ConsultancyTypeMaganinComponent } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin.component';
import { ConsultancyTypeMaganinService } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin.service';
import { ConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';

describe('Component Tests', () => {
  describe('ConsultancyTypeMaganin Management Component', () => {
    let comp: ConsultancyTypeMaganinComponent;
    let fixture: ComponentFixture<ConsultancyTypeMaganinComponent>;
    let service: ConsultancyTypeMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyTypeMaganinComponent],
      })
        .overrideTemplate(ConsultancyTypeMaganinComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsultancyTypeMaganinComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsultancyTypeMaganinService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConsultancyTypeMaganin(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.consultancyTypes && comp.consultancyTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
