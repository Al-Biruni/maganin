import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContentMaganin } from 'app/shared/model/content-maganin.model';

type EntityResponseType = HttpResponse<IContentMaganin>;
type EntityArrayResponseType = HttpResponse<IContentMaganin[]>;

@Injectable({ providedIn: 'root' })
export class ContentMaganinService {
  public resourceUrl = SERVER_API_URL + 'api/creations';

  constructor(protected http: HttpClient) {}

  create(content: IContentMaganin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .post<IContentMaganin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(content: IContentMaganin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .put<IContentMaganin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContentMaganin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContentMaganin[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(content: IContentMaganin): IContentMaganin {
    const copy: IContentMaganin = Object.assign({}, content, {
      dateAdded: content.dateAdded && content.dateAdded.isValid() ? content.dateAdded.toJSON() : undefined,
      expire: content.expire && content.expire.isValid() ? content.expire.toJSON() : undefined,
      dateEnd: content.dateEnd && content.dateEnd.isValid() ? content.dateEnd.toJSON() : undefined,
      dateLastMod: content.dateLastMod && content.dateLastMod.isValid() ? content.dateLastMod.toJSON() : undefined,
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
      res.body['content'].forEach((content: IContentMaganin) => {
        content.dateAdded = content.dateAdded ? moment(content.dateAdded) : undefined;
        content.expire = content.expire ? moment(content.expire) : undefined;
        content.dateEnd = content.dateEnd ? moment(content.dateEnd) : undefined;
        content.dateLastMod = content.dateLastMod ? moment(content.dateLastMod) : undefined;
      });
    }
    return res;
  }
}
