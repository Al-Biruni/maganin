import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContent } from 'app/shared/model/content.model';

type EntityResponseType = HttpResponse<IContent>;
type EntityArrayResponseType = HttpResponse<IContent[]>;

@Injectable({ providedIn: 'root' })
export class ContentService {
  public resourceUrl = SERVER_API_URL + 'api/contents';

  constructor(protected http: HttpClient) {}

  create(content: IContent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .post<IContent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(content: IContent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .put<IContent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(content: IContent): IContent {
    const copy: IContent = Object.assign({}, content, {
      dateAdded: content.dateAdded && content.dateAdded.isValid() ? content.dateAdded.format(DATE_FORMAT) : undefined,
      expire: content.expire && content.expire.isValid() ? content.expire.format(DATE_FORMAT) : undefined,
      dateEnd: content.dateEnd && content.dateEnd.isValid() ? content.dateEnd.format(DATE_FORMAT) : undefined,
      dateLastMod: content.dateLastMod && content.dateLastMod.isValid() ? content.dateLastMod.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAdded = res.body.dateAdded ? moment(res.body.dateAdded) : undefined;
      res.body.expire = res.body.expire ? moment(res.body.expire) : undefined;
      res.body.dateEnd = res.body.dateEnd ? moment(res.body.dateEnd) : undefined;
      res.body.dateLastMod = res.body.dateLastMod ? moment(res.body.dateLastMod) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((content: IContent) => {
        content.dateAdded = content.dateAdded ? moment(content.dateAdded) : undefined;
        content.expire = content.expire ? moment(content.expire) : undefined;
        content.dateEnd = content.dateEnd ? moment(content.dateEnd) : undefined;
        content.dateLastMod = content.dateLastMod ? moment(content.dateLastMod) : undefined;
      });
    }
    return res;
  }
}
