import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IConsultancyMaganin, ConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';
import { ConsultancyMaganinService } from './consultancy-maganin.service';
import { ConsultancyMaganinComponent } from './consultancy-maganin.component';
import { ConsultancyMaganinDetailComponent } from './consultancy-maganin-detail.component';
import { ConsultancyMaganinUpdateComponent } from './consultancy-maganin-update.component';

@Injectable({ providedIn: 'root' })
export class ConsultancyMaganinResolve implements Resolve<IConsultancyMaganin> {
  constructor(private service: ConsultancyMaganinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConsultancyMaganin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((consultancy: HttpResponse<ConsultancyMaganin>) => {
          if (consultancy.body) {
            return of(consultancy.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ConsultancyMaganin());
  }
}

export const consultancyRoute: Routes = [
  {
    path: '',
    component: ConsultancyMaganinComponent,
    data: {
      authorities: [],
      defaultSort: 'id,desc',
      pageTitle: 'maganinApp.consultancy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ConsultancyMaganinDetailComponent,
    resolve: {
      consultancy: ConsultancyMaganinResolve,
    },
    data: {
      authorities: [],
      pageTitle: 'maganinApp.consultancy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ConsultancyMaganinUpdateComponent,
    resolve: {
      consultancy: ConsultancyMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.consultancy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ConsultancyMaganinUpdateComponent,
    resolve: {
      consultancy: ConsultancyMaganinResolve,
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'maganinApp.consultancy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
