import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ConsultancyMaganinUpdateComponent } from 'app/entities/consultancy-maganin/consultancy-maganin-update.component';
import { ConsultancyMaganinService } from 'app/entities/consultancy-maganin/consultancy-maganin.service';
import { ConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';

describe('Component Tests', () => {
  describe('ConsultancyMaganin Management Update Component', () => {
    let comp: ConsultancyMaganinUpdateComponent;
    let fixture: ComponentFixture<ConsultancyMaganinUpdateComponent>;
    let service: ConsultancyMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyMaganinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConsultancyMaganinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsultancyMaganinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsultancyMaganinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConsultancyMaganin(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConsultancyMaganin();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
