import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FondDocumentEdocService } from 'app/entities/fond-document-edoc/fond-document-edoc.service';
import { IFondDocumentEdoc, FondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';

describe('Service Tests', () => {
  describe('FondDocumentEdoc Service', () => {
    let injector: TestBed;
    let service: FondDocumentEdocService;
    let httpMock: HttpTestingController;
    let elemDefault: IFondDocumentEdoc;
    let expectedResult: IFondDocumentEdoc | IFondDocumentEdoc[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FondDocumentEdocService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new FondDocumentEdoc(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FondDocumentEdoc', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new FondDocumentEdoc()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FondDocumentEdoc', () => {
        const returnedFromService = Object.assign(
          {
            denominationAr: 'BBBBBB',
            denominationFr: 'BBBBBB',
            formatPj: 'BBBBBB',
            reference: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FondDocumentEdoc', () => {
        const returnedFromService = Object.assign(
          {
            denominationAr: 'BBBBBB',
            denominationFr: 'BBBBBB',
            formatPj: 'BBBBBB',
            reference: 'BBBBBB',
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

      it('should delete a FondDocumentEdoc', () => {
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
