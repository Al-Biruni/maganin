import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { ContentMaganinComponent } from './content-maganin.component';
import { ContentMaganinDetailComponent } from './content-maganin-detail.component';
import { ContentMaganinUpdateComponent } from './content-maganin-update.component';
import { ContentMaganinDeleteDialogComponent } from './content-maganin-delete-dialog.component';
import { contentRoute } from './content-maganin.route';

@NgModule({
  imports: [MaganinSharedModule, RouterModule.forChild(contentRoute)],
  declarations: [
    ContentMaganinComponent,
    ContentMaganinDetailComponent,
    ContentMaganinUpdateComponent,
    ContentMaganinDeleteDialogComponent,
  ],
  entryComponents: [ContentMaganinDeleteDialogComponent],
})
export class MaganinContentMaganinModule {}
