import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { HomeService } from './home.service';
import { ArticleSummary, IArticleSummary } from 'app/shared/model/article-summery.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  images = [
    'content/images/people-connecting-jigsaw-pieces-head-together.jpg',
    'content/images/woman-visiting-psychologist.jpg',
    'content/images/people-connecting-jigsaw-pieces-head-together.jpg',
  ];
  latestArticles = 'أخر المقالات';
  latestArticlesSummary: ArticleSummary[] = [];

  constructor(private accountService: AccountService, private loginModalService: LoginModalService, private homeService: HomeService) {}
  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.homeService.find(1).subscribe((res: HttpResponse<IArticleSummary[]>) => {
      this.latestArticlesSummary = res.body || [];
    });
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
