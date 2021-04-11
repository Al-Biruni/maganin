import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ArticleMaganinService } from 'app/entities/article-maganin/article-maganin.service';
import { IArticleMaganin, ArticleMaganin } from 'app/shared/model/article-maganin.model';

describe('Service Tests', () => {
  describe('ArticleMaganin Service', () => {
    let injector: TestBed;
    let service: ArticleMaganinService;
    let httpMock: HttpTestingController;
    let elemDefault: IArticleMaganin;
    let expectedResult: IArticleMaganin | IArticleMaganin[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ArticleMaganinService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ArticleMaganin(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateAdded: currentDate.format(DATE_TIME_FORMAT),
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ArticleMaganin', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAdded: currentDate.format(DATE_TIME_FORMAT),
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            dateLastMod: currentDate,
          },
          returnedFromService
        );

        service.create(new ArticleMaganin()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ArticleMaganin', () => {
        const returnedFromService = Object.assign(
          {
            userId: 1,
            title: 'BBBBBB',
            author: 'BBBBBB',
            relatedURL: 'BBBBBB',
            dateAdded: currentDate.format(DATE_TIME_FORMAT),
            shortDesc: 'BBBBBB',
            longDesc: 'BBBBBB',
            display: 'BBBBBB',
            accessLevel: 1,
            impressions: 1,
            avgRating: 1,
            thumbnail: 'BBBBBB',
            keywords: 'BBBBBB',
            country: 'BBBBBB',
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            dateLastMod: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ArticleMaganin', () => {
        const returnedFromService = Object.assign(
          {
            userId: 1,
            title: 'BBBBBB',
            author: 'BBBBBB',
            relatedURL: 'BBBBBB',
            dateAdded: currentDate.format(DATE_TIME_FORMAT),
            shortDesc: 'BBBBBB',
            longDesc: 'BBBBBB',
            display: 'BBBBBB',
            accessLevel: 1,
            impressions: 1,
            avgRating: 1,
            thumbnail: 'BBBBBB',
            keywords: 'BBBBBB',
            country: 'BBBBBB',
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            dateLastMod: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ArticleMaganin', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
