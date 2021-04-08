import { Injectable } from '@angular/core';
import { SERVER_API_URL } from '../../app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IArticle } from '../../shared/model/article.model';
import { IArticleSummary } from '../../shared/model/article-summery.model';

type ArticleResponseType = HttpResponse<IArticle>;

type ArticleSummaryArrayResponseType = HttpResponse<IArticleSummary[]>;

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  public resourceUrl = SERVER_API_URL + 'api/articles';
  public articleUrl = SERVER_API_URL + 'api/articles/page';
  constructor(protected http: HttpClient) {}

  find(id: number): Observable<ArticleResponseType> {
    return this.http.get<IArticle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTopArticles(pageNum: number): Observable<ArticleSummaryArrayResponseType> {
    return this.http.get<IArticleSummary[]>(`${this.resourceUrl}?pageNum=${pageNum}&sort=impressions`, { observe: 'response' });
  }

  getLatestArticles(pageNum: number): Observable<ArticleSummaryArrayResponseType> {
    return this.http.get<IArticleSummary[]>(`${this.resourceUrl}?pageNum=${pageNum}&sort=dateLastMod`, { observe: 'response' });
  }
}
