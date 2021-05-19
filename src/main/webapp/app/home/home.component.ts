import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { HttpResponse } from '@angular/common/http';
import { ConsultancySummery, IConsultancySummery } from 'app/shared/model/consultancy-summery.model';
import { ArticleMaganinService } from 'app/entities/article-maganin/article-maganin.service';
import { ConsultancyMaganinService } from 'app/entities/consultancy-maganin/consultancy-maganin.service';
import { IArticleMaganin } from 'app/shared/model/article-maganin.model';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  images = [
    'content/images/Carousel Photos/woman-holding-hands-together-talking-with-counselor.jpg',
    'content/images//Carousel Photos/reading-articles-large.jpg',
  ];
  latestArticles = 'أخر المقالات';
  arConTitle = 'أخر الإستشارت';
  latestArticlesSummary: IArticleMaganin[] = [];

  latestConsultancies: ConsultancySummery[] = [];

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private articleService: ArticleMaganinService,
    private consultancyService: ConsultancyMaganinService
  ) {}
  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.articleService.getLatestArticles(1).subscribe((res: HttpResponse<IArticleMaganin[]>) => {
      this.latestArticlesSummary = res.body || [];
    });

    this.consultancyService.getLatestConsultancies().subscribe((res: HttpResponse<IConsultancySummery[]>) => {
      this.latestConsultancies = res.body || [];
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
