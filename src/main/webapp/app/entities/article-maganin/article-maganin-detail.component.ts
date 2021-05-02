import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IArticleMaganin } from 'app/shared/model/article-maganin.model';
import { AccountService } from '../../core/auth/account.service';

@Component({
  selector: 'jhi-article-maganin-detail',
  templateUrl: './article-maganin-detail.component.html',
  styleUrls: ['article-maganin-detail.component.scss'],
})
export class ArticleMaganinDetailComponent implements OnInit {
  article: IArticleMaganin | null = null;

  constructor(protected activatedRoute: ActivatedRoute, protected accountService: AccountService) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ article }) => (this.article = article));
  }

  previousState(): void {
    window.history.back();
  }
  isAdmin(): boolean {
    return this.accountService.hasAnyAuthority('ROLE_ADMIN');
  }
}
