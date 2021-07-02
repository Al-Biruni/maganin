import { NgModule } from '@angular/core';
import { ArticleSummeryComponent } from './article-summery/article-summery.component';
import { ArticleSummeryListComponent } from './article-summery-list.component';
import { MaganinSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { ReadMoreComponent } from './read-more/read-more.component';

@NgModule({
  imports: [MaganinSharedModule, RouterModule],
  declarations: [ArticleSummeryComponent, ArticleSummeryListComponent, ReadMoreComponent],
  exports: [ArticleSummeryListComponent, ArticleSummeryComponent, ReadMoreComponent],
})
export class ArticleSummeryListModule {}
