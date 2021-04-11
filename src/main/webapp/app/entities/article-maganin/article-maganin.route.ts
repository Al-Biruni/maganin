import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IArticleMaganin, ArticleMaganin } from 'app/shared/model/article-maganin.model';
import { ArticleMaganinService } from './article-maganin.service';
import { ArticleMaganinComponent } from './article-maganin.component';
import { ArticleMaganinDetailComponent } from './article-maganin-detail.component';
import { ArticleMaganinUpdateComponent } from './article-maganin-update.component';

@Injectable({ providedIn: 'root' })
export class ArticleMaganinResolve implements Resolve<IArticleMaganin> {
  constructor(private service: ArticleMaganinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IArticleMaganin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((article: HttpResponse<ArticleMaganin>) => {
          if (article.body) {
            return of(article.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ArticleMaganin());
  }
}

export const articleRoute: Routes = [
  {
    path: '',
    component: ArticleMaganinComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'maganinApp.article.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ArticleMaganinDetailComponent,
    resolve: {
      article: ArticleMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.article.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ArticleMaganinUpdateComponent,
    resolve: {
      article: ArticleMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.article.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ArticleMaganinUpdateComponent,
    resolve: {
      article: ArticleMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.article.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
