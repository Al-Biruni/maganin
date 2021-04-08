import { Component, OnInit } from '@angular/core';
import { ArticleSummary } from '../../../shared/model/article-summery.model';
import { ArticleService } from '../article.service';
import { HttpResponse } from '@angular/common/http';
import { IArticleSummary } from '../../../shared/model/article-summery.model';

@Component({
  selector: 'jhi-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss'],
})
export class ArticleListComponent implements OnInit {
  topArticles: ArticleSummary[] | undefined;
  latestArticles: ArticleSummary[] | undefined;
  latestArticlesArWord = 'أخر المقالات';

  constructor(articleService: ArticleService) {
    articleService.getTopArticles(1).subscribe((response: HttpResponse<IArticleSummary[]>) => (this.topArticles = response.body || []));
    articleService
      .getLatestArticles(1)
      .subscribe((response: HttpResponse<IArticleSummary[]>) => (this.latestArticles = response.body || []));
  }

  ngOnInit(): void {}
}
