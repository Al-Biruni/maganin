import { NgModule } from '@angular/core';
import { ArticleSummeryComponent } from './article-summery/article-summery.component';
import { ArticleSummeryListComponent } from './article-summery-list.component';
import { DukesdesignsSharedModule } from 'app/shared/shared.module';

@NgModule({
  imports: [DukesdesignsSharedModule],
  declarations: [ArticleSummeryComponent, ArticleSummeryListComponent],
  exports: [ArticleSummeryListComponent, ArticleSummeryComponent],
})
export class ArticleSummeryListModule {}
