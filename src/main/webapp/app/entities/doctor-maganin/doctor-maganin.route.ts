import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDoctorMaganin, Doctor } from 'app/shared/model/doctor.model';
import { DoctorService } from './doctor-maganin.service';

import { DoctorListComponent } from './listing-page/doctor-maganin.component';

@Injectable({ providedIn: 'root' })
export class DoctorResolve implements Resolve<IDoctorMaganin> {
  constructor(private service: DoctorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDoctorMaganin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((doctor: HttpResponse<Doctor>) => {
          if (doctor.body) {
            return of(doctor.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Doctor());
  }
}

export const doctorRoute: Routes = [
  {
    path: '',
    component: DoctorListComponent,
    data: {
      authorities: [],
      defaultSort: 'avgRating,des',
      pageTitle: 'maganinApp.doctor.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  // {
  //   path: ':id/view',
  //   component: ArticleMaganinDetailComponent,
  //   resolve: {
  //     article: ArticleMaganinResolve,
  //   },
  //   data: {
  //     authorities: [],
  //     pageTitle: 'maganinApp.article.home.title',
  //   },
  //   canActivate: [UserRouteAccessService],
  // },
  // {
  //   path: 'new',
  //   component: ArticleMaganinUpdateComponent,
  //   resolve: {
  //     article: ArticleMaganinResolve,
  //   },
  //   data: {
  //     authorities: [Authority.USER],
  //     pageTitle: 'maganinApp.article.home.title',
  //   },
  //   canActivate: [UserRouteAccessService],
  // },
  // {
  //   path: ':id/edit',
  //   component: ArticleMaganinUpdateComponent,
  //   resolve: {
  //     article: ArticleMaganinResolve,
  //   },
  //   data: {
  //     authorities: [Authority.ADMIN],
  //     pageTitle: 'maganinApp.article.home.title',
  //   },
  //   canActivate: [UserRouteAccessService],
  // },
];
