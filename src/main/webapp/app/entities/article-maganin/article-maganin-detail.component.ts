import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IArticleMaganin } from 'app/shared/model/article-maganin.model';

@Component({
  selector: 'jhi-article-maganin-detail',
  templateUrl: './article-maganin-detail.component.html',
})
export class ArticleMaganinDetailComponent implements OnInit {
  article: IArticleMaganin | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ article }) => (this.article = article));
  }

  previousState(): void {
    window.history.back();
  }
}
