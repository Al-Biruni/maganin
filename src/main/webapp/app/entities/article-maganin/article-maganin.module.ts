import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { ArticleMaganinComponent } from './article-maganin.component';
import { ArticleMaganinDetailComponent } from './article-maganin-detail.component';
import { ArticleMaganinUpdateComponent } from './article-maganin-update.component';
import { ArticleMaganinDeleteDialogComponent } from './article-maganin-delete-dialog.component';
import { articleRoute } from './article-maganin.route';
import { MaganinEditorModule } from '../../shared/htmlEditor/editor-maganin.module';
import { ArticleSummeryListModule } from '../../layouts/article-list/article-summery-list.module';
@NgModule({
  imports: [MaganinSharedModule, MaganinEditorModule, RouterModule.forChild(articleRoute), ArticleSummeryListModule],
  declarations: [
    ArticleMaganinComponent,
    ArticleMaganinDetailComponent,
    ArticleMaganinUpdateComponent,
    ArticleMaganinDeleteDialogComponent,
  ],
  entryComponents: [ArticleMaganinDeleteDialogComponent],
})
export class MaganinArticleMaganinModule {}
