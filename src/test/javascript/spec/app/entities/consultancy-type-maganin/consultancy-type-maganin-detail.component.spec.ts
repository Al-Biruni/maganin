import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ConsultancyTypeMaganinDetailComponent } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin-detail.component';
import { ConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';

describe('Component Tests', () => {
  describe('ConsultancyTypeMaganin Management Detail Component', () => {
    let comp: ConsultancyTypeMaganinDetailComponent;
    let fixture: ComponentFixture<ConsultancyTypeMaganinDetailComponent>;
    const route = ({ data: of({ consultancyType: new ConsultancyTypeMaganin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyTypeMaganinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConsultancyTypeMaganinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsultancyTypeMaganinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load consultancyType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.consultancyType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
