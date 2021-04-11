import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ContentMaganinService } from 'app/entities/content-maganin/content-maganin.service';
import { IContentMaganin, ContentMaganin } from 'app/shared/model/content-maganin.model';

describe('Service Tests', () => {
  describe('ContentMaganin Service', () => {
    let injector: TestBed;
    let service: ContentMaganinService;
    let httpMock: HttpTestingController;
    let elemDefault: IContentMaganin;
    let expectedResult: IContentMaganin | IContentMaganin[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ContentMaganinService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ContentMaganin(
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
        currentDate,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
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
            expire: currentDate.format(DATE_TIME_FORMAT),
            dateEnd: currentDate.format(DATE_TIME_FORMAT),
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ContentMaganin', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAdded: currentDate.format(DATE_TIME_FORMAT),
            expire: currentDate.format(DATE_TIME_FORMAT),
            dateEnd: currentDate.format(DATE_TIME_FORMAT),
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            expire: currentDate,
            dateEnd: currentDate,
            dateLastMod: currentDate,
          },
          returnedFromService
        );

        service.create(new ContentMaganin()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ContentMaganin', () => {
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
            expire: currentDate.format(DATE_TIME_FORMAT),
            priority: 1,
            impressions: 1,
            clickThrus: 1,
            avgRating: 1,
            ratings: 1,
            thumbnail: 'BBBBBB',
            image1: 'BBBBBB',
            dateEnd: currentDate.format(DATE_TIME_FORMAT),
            keywords: 'BBBBBB',
            creationsType: 'BBBBBB',
            wazn: 'BBBBBB',
            country: 'BBBBBB',
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            expire: currentDate,
            dateEnd: currentDate,
            dateLastMod: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ContentMaganin', () => {
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
            expire: currentDate.format(DATE_TIME_FORMAT),
            priority: 1,
            impressions: 1,
            clickThrus: 1,
            avgRating: 1,
            ratings: 1,
            thumbnail: 'BBBBBB',
            image1: 'BBBBBB',
            dateEnd: currentDate.format(DATE_TIME_FORMAT),
            keywords: 'BBBBBB',
            creationsType: 'BBBBBB',
            wazn: 'BBBBBB',
            country: 'BBBBBB',
            dateLastMod: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            expire: currentDate,
            dateEnd: currentDate,
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

      it('should delete a ContentMaganin', () => {
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
