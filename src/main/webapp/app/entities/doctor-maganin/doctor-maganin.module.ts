import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { DoctorListComponent } from './listing-page/doctor-maganin.component';
import { doctorRoute } from './doctor-maganin.route';
import { MaganinEditorModule } from '../../shared/htmlEditor/editor-maganin.module';
@NgModule({
  imports: [MaganinSharedModule, MaganinEditorModule, RouterModule.forChild(doctorRoute)],
  declarations: [DoctorListComponent],
  entryComponents: [],
})
export class DoctorModule {}
