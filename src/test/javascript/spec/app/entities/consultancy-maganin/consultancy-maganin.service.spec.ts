import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ConsultancyMaganinService } from 'app/entities/consultancy-maganin/consultancy-maganin.service';
import { IConsultancyMaganin, ConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';

describe('Service Tests', () => {
  describe('ConsultancyMaganin Service', () => {
    let injector: TestBed;
    let service: ConsultancyMaganinService;
    let httpMock: HttpTestingController;
    let elemDefault: IConsultancyMaganin;
    let expectedResult: IConsultancyMaganin | IConsultancyMaganin[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ConsultancyMaganinService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ConsultancyMaganin(
        0,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        false,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ConsultancyMaganin', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.create(new ConsultancyMaganin()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ConsultancyMaganin', () => {
        const returnedFromService = Object.assign(
          {
            userId: 1,
            name: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT),
            age: 'BBBBBB',
            gender: 'BBBBBB',
            religion: 'BBBBBB',
            rel2: 'BBBBBB',
            edu: 'BBBBBB',
            social: 'BBBBBB',
            econ: 'BBBBBB',
            hop: 'BBBBBB',
            job: 'BBBBBB',
            country: 'BBBBBB',
            origin: 'BBBBBB',
            email: 'BBBBBB',
            title: 'BBBBBB',
            question: 'BBBBBB',
            answer: 'BBBBBB',
            doctor: 'BBBBBB',
            consultantId: 1,
            show: true,
            paid: true,
            impressions: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ConsultancyMaganin', () => {
        const returnedFromService = Object.assign(
          {
            userId: 1,
            name: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT),
            age: 'BBBBBB',
            gender: 'BBBBBB',
            religion: 'BBBBBB',
            rel2: 'BBBBBB',
            edu: 'BBBBBB',
            social: 'BBBBBB',
            econ: 'BBBBBB',
            hop: 'BBBBBB',
            job: 'BBBBBB',
            country: 'BBBBBB',
            origin: 'BBBBBB',
            email: 'BBBBBB',
            title: 'BBBBBB',
            question: 'BBBBBB',
            answer: 'BBBBBB',
            doctor: 'BBBBBB',
            consultantId: 1,
            show: true,
            paid: true,
            impressions: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ConsultancyMaganin', () => {
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
