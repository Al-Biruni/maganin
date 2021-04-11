import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { IArticleSummary } from 'app/shared/model/article-summery.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';

type ArticleSummaryArrayResponseType = HttpResponse<IArticleSummary[]>;
@Injectable({
  providedIn: 'root',
})
export class HomeService {
  public resourceUrl = SERVER_API_URL + 'api/articles';

  constructor(protected http: HttpClient) {}

  find(pageNum: number): Observable<ArticleSummaryArrayResponseType> {
    return this.http.get<IArticleSummary[]>(`${this.resourceUrl}?pageNum=${pageNum}&sort=dateLastMod`, { observe: 'response' });
  }
}
