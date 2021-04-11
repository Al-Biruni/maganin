import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CategoryMaganinService } from 'app/entities/category-maganin/category-maganin.service';
import { ICategoryMaganin, CategoryMaganin } from 'app/shared/model/category-maganin.model';

describe('Service Tests', () => {
  describe('CategoryMaganin Service', () => {
    let injector: TestBed;
    let service: CategoryMaganinService;
    let httpMock: HttpTestingController;
    let elemDefault: ICategoryMaganin;
    let expectedResult: ICategoryMaganin | ICategoryMaganin[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CategoryMaganinService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CategoryMaganin(0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CategoryMaganin', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CategoryMaganin()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CategoryMaganin', () => {
        const returnedFromService = Object.assign(
          {
            categoryTypeId: 'BBBBBB',
            categoryName: 'BBBBBB',
            parentId: 1,
            description: 'BBBBBB',
            imageURL: 'BBBBBB',
            hideSearch: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CategoryMaganin', () => {
        const returnedFromService = Object.assign(
          {
            categoryTypeId: 'BBBBBB',
            categoryName: 'BBBBBB',
            parentId: 1,
            description: 'BBBBBB',
            imageURL: 'BBBBBB',
            hideSearch: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CategoryMaganin', () => {
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
