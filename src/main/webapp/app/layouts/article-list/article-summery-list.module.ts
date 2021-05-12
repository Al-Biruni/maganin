import { NgModule } from '@angular/core';
import { ArticleSummeryComponent } from './article-summery/article-summery.component';
import { ArticleSummeryListComponent } from './article-summery-list.component';
import { MaganinSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [MaganinSharedModule, RouterModule],
  declarations: [ArticleSummeryComponent, ArticleSummeryListComponent],
  exports: [ArticleSummeryListComponent, ArticleSummeryComponent],
})
export class ArticleSummeryListModule {}
