import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { CategoryMaganinDetailComponent } from 'app/entities/category-maganin/category-maganin-detail.component';
import { CategoryMaganin } from 'app/shared/model/category-maganin.model';

describe('Component Tests', () => {
  describe('CategoryMaganin Management Detail Component', () => {
    let comp: CategoryMaganinDetailComponent;
    let fixture: ComponentFixture<CategoryMaganinDetailComponent>;
    const route = ({ data: of({ category: new CategoryMaganin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [CategoryMaganinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CategoryMaganinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryMaganinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load category on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.category).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
