import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { ConsultancyMaganinComponent } from './consultancy-maganin.component';
import { ConsultancyMaganinDetailComponent } from './consultancy-maganin-detail.component';
import { ConsultancyMaganinUpdateComponent } from './consultancy-maganin-update.component';
import { ConsultancyMaganinDeleteDialogComponent } from './consultancy-maganin-delete-dialog.component';
import { consultancyRoute } from './consultancy-maganin.route';
import { MaganinEditorModule } from '../../shared/htmlEditor/editor-maganin.module';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { ConsultancySummeryListModule } from '../../layouts/consultancy-list/consultancy-summery-list.module';

@NgModule({
  imports: [
    MaganinSharedModule,
    MaganinEditorModule,
    RouterModule.forChild(consultancyRoute),
    MatIconModule,
    MatMenuModule,
    ConsultancySummeryListModule,
  ],
  declarations: [
    ConsultancyMaganinComponent,
    ConsultancyMaganinDetailComponent,
    ConsultancyMaganinUpdateComponent,
    ConsultancyMaganinDeleteDialogComponent,
  ],
  entryComponents: [ConsultancyMaganinDeleteDialogComponent],
})
export class MaganinConsultancyMaganinModule {}
