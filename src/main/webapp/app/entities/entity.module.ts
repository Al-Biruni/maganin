import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'content',
        loadChildren: () => import('./content/content.module').then(m => m.DukesdesignsContentModule),
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.DukesdesignsCategoryModule),
      },
      {
        path: 'مقالات',
        loadChildren: () => import('./article/article.module').then(m => m.ArticleModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
  declarations: [],
})
export class DukesdesignsEntityModule {}
