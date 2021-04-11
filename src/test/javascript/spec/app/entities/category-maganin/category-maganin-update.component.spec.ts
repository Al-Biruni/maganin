import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { CategoryMaganinUpdateComponent } from 'app/entities/category-maganin/category-maganin-update.component';
import { CategoryMaganinService } from 'app/entities/category-maganin/category-maganin.service';
import { CategoryMaganin } from 'app/shared/model/category-maganin.model';

describe('Component Tests', () => {
  describe('CategoryMaganin Management Update Component', () => {
    let comp: CategoryMaganinUpdateComponent;
    let fixture: ComponentFixture<CategoryMaganinUpdateComponent>;
    let service: CategoryMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [CategoryMaganinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CategoryMaganinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoryMaganinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryMaganinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoryMaganin(123);
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
        const entity = new CategoryMaganin();
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
