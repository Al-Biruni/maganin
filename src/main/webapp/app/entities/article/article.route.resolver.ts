import { Injectable } from '@angular/core';
import { Router, Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { ArticleService } from './article.service';
import { Article, IArticle } from '../../shared/model/article.model';
import { flatMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Authority } from '../../shared/constants/authority.constants';
import { UserRouteAccessService } from '../../core/auth/user-route-access-service';
import { CategoryUpdateComponent } from '../category/category-update.component';
import { CategoryResolve } from '../category/category.route';
import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleComponent } from './article.component';

@Injectable({
  providedIn: 'root',
})
export class ArticleResolver implements Resolve<IArticle> {
  constructor(private service: ArticleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IArticle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((article: HttpResponse<Article>) => {
          if (article.body) {
            return of(article.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Article());
  }
}
export const articleRoute: Routes = [
  {
    path: '',
    component: ArticleListComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'dukesdesignsApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ArticleComponent,
    resolve: {
      category: CategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'dukesdesignsApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategoryUpdateComponent,
    resolve: {
      category: CategoryResolve,
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'dukesdesignsApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
