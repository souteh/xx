import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';

type EntityResponseType = HttpResponse<ITypeDeContenuEdoc>;
type EntityArrayResponseType = HttpResponse<ITypeDeContenuEdoc[]>;

@Injectable({ providedIn: 'root' })
export class TypeDeContenuEdocService {
  public resourceUrl = SERVER_API_URL + 'api/type-de-contenus';

  constructor(protected http: HttpClient) {}

  create(typeDeContenu: ITypeDeContenuEdoc): Observable<EntityResponseType> {
    return this.http.post<ITypeDeContenuEdoc>(this.resourceUrl, typeDeContenu, { observe: 'response' });
  }

  update(typeDeContenu: ITypeDeContenuEdoc): Observable<EntityResponseType> {
    return this.http.put<ITypeDeContenuEdoc>(this.resourceUrl, typeDeContenu, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeDeContenuEdoc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeDeContenuEdoc[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
