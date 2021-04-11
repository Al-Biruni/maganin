import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MaganinTestModule } from '../../../test.module';
import { ContentMaganinDetailComponent } from 'app/entities/content-maganin/content-maganin-detail.component';
import { ContentMaganin } from 'app/shared/model/content-maganin.model';

describe('Component Tests', () => {
  describe('ContentMaganin Management Detail Component', () => {
    let comp: ContentMaganinDetailComponent;
    let fixture: ComponentFixture<ContentMaganinDetailComponent>;
    const route = ({ data: of({ content: new ContentMaganin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ContentMaganinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ContentMaganinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContentMaganinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load content on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.content).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
