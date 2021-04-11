import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategoryMaganin } from 'app/shared/model/category-maganin.model';

type EntityResponseType = HttpResponse<ICategoryMaganin>;
type EntityArrayResponseType = HttpResponse<ICategoryMaganin[]>;

@Injectable({ providedIn: 'root' })
export class CategoryMaganinService {
  public resourceUrl = SERVER_API_URL + 'api/categories';

  constructor(protected http: HttpClient) {}

  create(category: ICategoryMaganin): Observable<EntityResponseType> {
    return this.http.post<ICategoryMaganin>(this.resourceUrl, category, { observe: 'response' });
  }

  update(category: ICategoryMaganin): Observable<EntityResponseType> {
    return this.http.put<ICategoryMaganin>(this.resourceUrl, category, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryMaganin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryMaganin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
