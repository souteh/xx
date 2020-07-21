import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TypeDeContenuEdocService } from 'app/entities/type-de-contenu-edoc/type-de-contenu-edoc.service';
import { ITypeDeContenuEdoc, TypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';

describe('Service Tests', () => {
  describe('TypeDeContenuEdoc Service', () => {
    let injector: TestBed;
    let service: TypeDeContenuEdocService;
    let httpMock: HttpTestingController;
    let elemDefault: ITypeDeContenuEdoc;
    let expectedResult: ITypeDeContenuEdoc | ITypeDeContenuEdoc[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TypeDeContenuEdocService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TypeDeContenuEdoc(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TypeDeContenuEdoc', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TypeDeContenuEdoc()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TypeDeContenuEdoc', () => {
        const returnedFromService = Object.assign(
          {
            denominationAr: 'BBBBBB',
            denominationFr: 'BBBBBB',
            reference: 'BBBBBB',
            code: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TypeDeContenuEdoc', () => {
        const returnedFromService = Object.assign(
          {
            denominationAr: 'BBBBBB',
            denominationFr: 'BBBBBB',
            reference: 'BBBBBB',
            code: 'BBBBBB',
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

      it('should delete a TypeDeContenuEdoc', () => {
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
