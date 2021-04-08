import { NgModule } from '@angular/core';
import { DukesdesignsSharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { articleRoute } from './article.route.resolver';
import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleComponent } from './article.component';
import { ArticleSummeryListModule } from '../../layouts/article-summery-list/article-summery-list.module';

@NgModule({
  imports: [DukesdesignsSharedModule, RouterModule.forChild(articleRoute), ArticleSummeryListModule],
  declarations: [ArticleComponent, ArticleListComponent],
})
export class ArticleModule {}
