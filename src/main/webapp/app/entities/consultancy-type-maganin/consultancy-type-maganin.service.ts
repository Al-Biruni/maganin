import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';

type EntityResponseType = HttpResponse<IConsultancyTypeMaganin>;
type EntityArrayResponseType = HttpResponse<IConsultancyTypeMaganin[]>;

@Injectable({ providedIn: 'root' })
export class ConsultancyTypeMaganinService {
  public resourceUrl = SERVER_API_URL + 'api/consultancy-types';

  constructor(protected http: HttpClient) {}

  create(consultancyType: IConsultancyTypeMaganin): Observable<EntityResponseType> {
    return this.http.post<IConsultancyTypeMaganin>(this.resourceUrl, consultancyType, { observe: 'response' });
  }

  update(consultancyType: IConsultancyTypeMaganin): Observable<EntityResponseType> {
    return this.http.put<IConsultancyTypeMaganin>(this.resourceUrl, consultancyType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConsultancyTypeMaganin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConsultancyTypeMaganin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
