import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ContentMaganinUpdateComponent } from 'app/entities/content-maganin/content-maganin-update.component';
import { ContentMaganinService } from 'app/entities/content-maganin/content-maganin.service';
import { ContentMaganin } from 'app/shared/model/content-maganin.model';

describe('Component Tests', () => {
  describe('ContentMaganin Management Update Component', () => {
    let comp: ContentMaganinUpdateComponent;
    let fixture: ComponentFixture<ContentMaganinUpdateComponent>;
    let service: ContentMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ContentMaganinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ContentMaganinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContentMaganinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContentMaganinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContentMaganin(123);
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
        const entity = new ContentMaganin();
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
