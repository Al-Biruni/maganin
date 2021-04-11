import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ConsultancyMaganinDetailComponent } from 'app/entities/consultancy-maganin/consultancy-maganin-detail.component';
import { ConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';

describe('Component Tests', () => {
  describe('ConsultancyMaganin Management Detail Component', () => {
    let comp: ConsultancyMaganinDetailComponent;
    let fixture: ComponentFixture<ConsultancyMaganinDetailComponent>;
    const route = ({ data: of({ consultancy: new ConsultancyMaganin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyMaganinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConsultancyMaganinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsultancyMaganinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load consultancy on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.consultancy).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
