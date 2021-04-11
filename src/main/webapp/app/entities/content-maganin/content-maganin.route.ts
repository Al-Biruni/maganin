import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContentMaganin, ContentMaganin } from 'app/shared/model/content-maganin.model';
import { ContentMaganinService } from './content-maganin.service';
import { ContentMaganinComponent } from './content-maganin.component';
import { ContentMaganinDetailComponent } from './content-maganin-detail.component';
import { ContentMaganinUpdateComponent } from './content-maganin-update.component';

@Injectable({ providedIn: 'root' })
export class ContentMaganinResolve implements Resolve<IContentMaganin> {
  constructor(private service: ContentMaganinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContentMaganin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((content: HttpResponse<ContentMaganin>) => {
          if (content.body) {
            return of(content.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ContentMaganin());
  }
}

export const contentRoute: Routes = [
  {
    path: '',
    component: ContentMaganinComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.content.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ContentMaganinDetailComponent,
    resolve: {
      content: ContentMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.content.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ContentMaganinUpdateComponent,
    resolve: {
      content: ContentMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.content.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ContentMaganinUpdateComponent,
    resolve: {
      content: ContentMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.content.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
