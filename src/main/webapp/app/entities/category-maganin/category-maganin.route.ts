import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategoryMaganin, CategoryMaganin } from 'app/shared/model/category-maganin.model';
import { CategoryMaganinService } from './category-maganin.service';
import { CategoryMaganinComponent } from './category-maganin.component';
import { CategoryMaganinDetailComponent } from './category-maganin-detail.component';
import { CategoryMaganinUpdateComponent } from './category-maganin-update.component';

@Injectable({ providedIn: 'root' })
export class CategoryMaganinResolve implements Resolve<ICategoryMaganin> {
  constructor(private service: CategoryMaganinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategoryMaganin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((category: HttpResponse<CategoryMaganin>) => {
          if (category.body) {
            return of(category.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategoryMaganin());
  }
}

export const categoryRoute: Routes = [
  {
    path: '',
    component: CategoryMaganinComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategoryMaganinDetailComponent,
    resolve: {
      category: CategoryMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategoryMaganinUpdateComponent,
    resolve: {
      category: CategoryMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CategoryMaganinUpdateComponent,
    resolve: {
      category: CategoryMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.category.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
