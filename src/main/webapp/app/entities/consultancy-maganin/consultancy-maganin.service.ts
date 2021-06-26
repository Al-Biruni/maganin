import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';
import { IConsultancySummery } from '../../shared/model/consultancy-summery.model';
import { IDoctorDTO } from '../../shared/model/doctorSummery.model';

type EntityResponseType = HttpResponse<IConsultancyMaganin>;
type EntityArrayResponseType = HttpResponse<IConsultancySummery[]>;
type doctorEntityArrayResponseType = HttpResponse<IDoctorDTO[]>;

@Injectable({ providedIn: 'root' })
export class ConsultancyMaganinService {
  public resourceUrl = SERVER_API_URL + 'api/consultancies';
  public doctorSummeryResourceUrl = SERVER_API_URL + 'api/doctors/summery';

  constructor(protected http: HttpClient) {}

  create(consultancy: IConsultancyMaganin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(consultancy);
    return this.http
      .post<IConsultancyMaganin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(consultancy: IConsultancyMaganin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(consultancy);
    return this.http
      .put<IConsultancyMaganin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IConsultancyMaganin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConsultancySummery[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
  queryPublished(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConsultancySummery[]>(this.resourceUrl + '/published', { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(consultancy: IConsultancyMaganin): IConsultancyMaganin {
    const copy: IConsultancyMaganin = Object.assign({}, consultancy, {
      date: consultancy.dateAdded && consultancy.dateAdded.isValid() ? consultancy.dateAdded.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAdded = res.body.dateAdded ? moment(res.body.dateAdded) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((consultancy: IConsultancySummery) => {
        consultancy.dateAdded = consultancy.dateAdded ? moment(consultancy.dateAdded) : undefined;
      });
    }
    return res;
  }

  getDoctors(): Observable<doctorEntityArrayResponseType> {
    return this.http.get<IDoctorDTO[]>(this.doctorSummeryResourceUrl, { observe: 'response' });
  }
  getLatestConsultancies(): Observable<EntityArrayResponseType> {
    return this.http.get<IConsultancySummery[]>(`${this.resourceUrl + '/latest'}`, { observe: 'response' });
  }
}
