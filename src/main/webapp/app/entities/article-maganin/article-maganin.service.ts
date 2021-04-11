import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IArticleMaganin } from 'app/shared/model/article-maganin.model';

type EntityResponseType = HttpResponse<IArticleMaganin>;
type EntityArrayResponseType = HttpResponse<IArticleMaganin[]>;

@Injectable({ providedIn: 'root' })
export class ArticleMaganinService {
  public resourceUrl = SERVER_API_URL + 'api/articles';

  constructor(protected http: HttpClient) {}

  create(article: IArticleMaganin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(article);
    return this.http
      .post<IArticleMaganin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(article: IArticleMaganin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(article);
    return this.http
      .put<IArticleMaganin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IArticleMaganin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IArticleMaganin[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(article: IArticleMaganin): IArticleMaganin {
    const copy: IArticleMaganin = Object.assign({}, article, {
      dateAdded: article.dateAdded && article.dateAdded.isValid() ? article.dateAdded.toJSON() : undefined,
      dateLastMod: article.dateLastMod && article.dateLastMod.isValid() ? article.dateLastMod.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAdded = res.body.dateAdded ? moment(res.body.dateAdded) : undefined;
      res.body.dateLastMod = res.body.dateLastMod ? moment(res.body.dateLastMod) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((article: IArticleMaganin) => {
        article.dateAdded = article.dateAdded ? moment(article.dateAdded) : undefined;
        article.dateLastMod = article.dateLastMod ? moment(article.dateLastMod) : undefined;
      });
    }
    return res;
  }
}
