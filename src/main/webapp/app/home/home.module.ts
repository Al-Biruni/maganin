import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ArticleSummeryListModule } from 'app/layouts/article-list/article-summery-list.module';
import { ConsultancySummeryListModule } from 'app/layouts/consultancy-list/consultancy-summery-list.module';
import { DoctorListModule } from 'app/layouts/doctor-list/doctor-list.module';

@NgModule({
  imports: [
    MaganinSharedModule,
    RouterModule.forChild([HOME_ROUTE]),
    ArticleSummeryListModule,
    ConsultancySummeryListModule,
    DoctorListModule,
  ],
  declarations: [HomeComponent],
})
export class MaganinHomeModule {}
