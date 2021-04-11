import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ArticleMaganinDetailComponent } from 'app/entities/article-maganin/article-maganin-detail.component';
import { ArticleMaganin } from 'app/shared/model/article-maganin.model';

describe('Component Tests', () => {
  describe('ArticleMaganin Management Detail Component', () => {
    let comp: ArticleMaganinDetailComponent;
    let fixture: ComponentFixture<ArticleMaganinDetailComponent>;
    const route = ({ data: of({ article: new ArticleMaganin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ArticleMaganinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ArticleMaganinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ArticleMaganinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load article on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.article).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
