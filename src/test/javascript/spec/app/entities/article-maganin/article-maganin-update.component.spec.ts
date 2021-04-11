import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ArticleMaganinUpdateComponent } from 'app/entities/article-maganin/article-maganin-update.component';
import { ArticleMaganinService } from 'app/entities/article-maganin/article-maganin.service';
import { ArticleMaganin } from 'app/shared/model/article-maganin.model';

describe('Component Tests', () => {
  describe('ArticleMaganin Management Update Component', () => {
    let comp: ArticleMaganinUpdateComponent;
    let fixture: ComponentFixture<ArticleMaganinUpdateComponent>;
    let service: ArticleMaganinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ArticleMaganinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ArticleMaganinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ArticleMaganinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ArticleMaganinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ArticleMaganin(123);
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
        const entity = new ArticleMaganin();
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
