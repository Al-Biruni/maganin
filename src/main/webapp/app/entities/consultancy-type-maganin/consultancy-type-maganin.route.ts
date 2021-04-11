import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IConsultancyTypeMaganin, ConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';
import { ConsultancyTypeMaganinService } from './consultancy-type-maganin.service';
import { ConsultancyTypeMaganinComponent } from './consultancy-type-maganin.component';
import { ConsultancyTypeMaganinDetailComponent } from './consultancy-type-maganin-detail.component';
import { ConsultancyTypeMaganinUpdateComponent } from './consultancy-type-maganin-update.component';

@Injectable({ providedIn: 'root' })
export class ConsultancyTypeMaganinResolve implements Resolve<IConsultancyTypeMaganin> {
  constructor(private service: ConsultancyTypeMaganinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConsultancyTypeMaganin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((consultancyType: HttpResponse<ConsultancyTypeMaganin>) => {
          if (consultancyType.body) {
            return of(consultancyType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ConsultancyTypeMaganin());
  }
}

export const consultancyTypeRoute: Routes = [
  {
    path: '',
    component: ConsultancyTypeMaganinComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.consultancyType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ConsultancyTypeMaganinDetailComponent,
    resolve: {
      consultancyType: ConsultancyTypeMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.consultancyType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ConsultancyTypeMaganinUpdateComponent,
    resolve: {
      consultancyType: ConsultancyTypeMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.consultancyType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ConsultancyTypeMaganinUpdateComponent,
    resolve: {
      consultancyType: ConsultancyTypeMaganinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'maganinApp.consultancyType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
