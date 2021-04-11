import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { ConsultancyTypeMaganinComponent } from './consultancy-type-maganin.component';
import { ConsultancyTypeMaganinDetailComponent } from './consultancy-type-maganin-detail.component';
import { ConsultancyTypeMaganinUpdateComponent } from './consultancy-type-maganin-update.component';
import { ConsultancyTypeMaganinDeleteDialogComponent } from './consultancy-type-maganin-delete-dialog.component';
import { consultancyTypeRoute } from './consultancy-type-maganin.route';

@NgModule({
  imports: [MaganinSharedModule, RouterModule.forChild(consultancyTypeRoute)],
  declarations: [
    ConsultancyTypeMaganinComponent,
    ConsultancyTypeMaganinDetailComponent,
    ConsultancyTypeMaganinUpdateComponent,
    ConsultancyTypeMaganinDeleteDialogComponent,
  ],
  entryComponents: [ConsultancyTypeMaganinDeleteDialogComponent],
})
export class MaganinConsultancyTypeMaganinModule {}
