import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { CategoryMaganinComponent } from './category-maganin.component';
import { CategoryMaganinDetailComponent } from './category-maganin-detail.component';
import { CategoryMaganinUpdateComponent } from './category-maganin-update.component';
import { CategoryMaganinDeleteDialogComponent } from './category-maganin-delete-dialog.component';
import { categoryRoute } from './category-maganin.route';

@NgModule({
  imports: [MaganinSharedModule, RouterModule.forChild(categoryRoute)],
  declarations: [
    CategoryMaganinComponent,
    CategoryMaganinDetailComponent,
    CategoryMaganinUpdateComponent,
    CategoryMaganinDeleteDialogComponent,
  ],
  entryComponents: [CategoryMaganinDeleteDialogComponent],
})
export class MaganinCategoryMaganinModule {}
