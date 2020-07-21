import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';

type EntityResponseType = HttpResponse<IFondDocumentEdoc>;
type EntityArrayResponseType = HttpResponse<IFondDocumentEdoc[]>;

@Injectable({ providedIn: 'root' })
export class FondDocumentEdocService {
  public resourceUrl = SERVER_API_URL + 'api/fond-documents';

  constructor(protected http: HttpClient) {}

  create(fondDocument: IFondDocumentEdoc): Observable<EntityResponseType> {
    return this.http.post<IFondDocumentEdoc>(this.resourceUrl, fondDocument, { observe: 'response' });
  }

  update(fondDocument: IFondDocumentEdoc): Observable<EntityResponseType> {
    return this.http.put<IFondDocumentEdoc>(this.resourceUrl, fondDocument, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFondDocumentEdoc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFondDocumentEdoc[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
