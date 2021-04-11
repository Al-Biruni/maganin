import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ConsultancyTypeMaganinUpdateComponent } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin-update.component';
import { ConsultancyTypeMaganinService } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin.service';
import { ConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';

describe('Component Tests', () => {
  describe('ConsultancyTypeMaganin Management Update Component', () => {
    let comp: ConsultancyTypeMaganinUpdateComponent;
    let fixture: ComponentFixture<ConsultancyTypeMaganinUpdateComponent>;
    let service: ConsultancyTypeMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyTypeMaganinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConsultancyTypeMaganinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsultancyTypeMaganinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsultancyTypeMaganinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConsultancyTypeMaganin(123);
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
        const entity = new ConsultancyTypeMaganin();
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
