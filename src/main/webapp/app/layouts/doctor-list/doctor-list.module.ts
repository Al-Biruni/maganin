import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DoctorListComponent } from './doctor-list.component';
import { DoctorCardSummeryComponent } from './doctor-card-summery/doctor-card-summery.component';
import { RouterModule } from '@angular/router';
import { MaganinSharedModule } from 'app/shared/shared.module';

@NgModule({
  declarations: [DoctorListComponent, DoctorCardSummeryComponent],
  exports: [DoctorListComponent],
  imports: [CommonModule, RouterModule, MaganinSharedModule],
})
export class DoctorListModule {}
