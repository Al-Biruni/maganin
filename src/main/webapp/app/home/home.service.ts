import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { IArticleSummary } from 'app/shared/model/article-summery.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { IConsultancySummery } from 'app/shared/model/consultancy-summery.model';

type ArticleSummaryArrayResponseType = HttpResponse<IArticleSummary[]>;
type ConsultancySummeryArrayResponseType = HttpResponse<IConsultancySummery[]>;
@Injectable({
  providedIn: 'root',
})
export class HomeService {
  public articleResourceUrl = SERVER_API_URL + 'api/articles';
  public consultancyResourceUrl = SERVER_API_URL + 'api/consultancies/latest';

  constructor(protected http: HttpClient) {}

  getLatestArticles(pageNum: number): Observable<ArticleSummaryArrayResponseType> {
    return this.http.get<IArticleSummary[]>(`${this.articleResourceUrl}?pageNum=${pageNum}&sort=dateAdded`, { observe: 'response' });
  }
  getLatestConsultancies(): Observable<ConsultancySummeryArrayResponseType> {
    return this.http.get<IConsultancySummery[]>(`${this.consultancyResourceUrl}`, { observe: 'response' });
  }
}
