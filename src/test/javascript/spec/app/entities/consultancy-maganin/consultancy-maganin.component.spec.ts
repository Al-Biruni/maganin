import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { MaganinTestModule } from '../../../test.module';
import { ConsultancyMaganinComponent } from 'app/entities/consultancy-maganin/consultancy-maganin.component';
import { ConsultancyMaganinService } from 'app/entities/consultancy-maganin/consultancy-maganin.service';
import { ConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';

describe('Component Tests', () => {
  describe('ConsultancyMaganin Management Component', () => {
    let comp: ConsultancyMaganinComponent;
    let fixture: ComponentFixture<ConsultancyMaganinComponent>;
    let service: ConsultancyMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyMaganinComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(ConsultancyMaganinComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsultancyMaganinComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsultancyMaganinService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConsultancyMaganin(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.consultancies && comp.consultancies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConsultancyMaganin(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.consultancies && comp.consultancies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
