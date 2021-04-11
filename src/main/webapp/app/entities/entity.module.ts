import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'article-maganin',
        loadChildren: () => import('./article-maganin/article-maganin.module').then(m => m.MaganinArticleMaganinModule),
      },
      {
        path: 'content-maganin',
        loadChildren: () => import('./content-maganin/content-maganin.module').then(m => m.MaganinContentMaganinModule),
      },
      {
        path: 'category-maganin',
        loadChildren: () => import('./category-maganin/category-maganin.module').then(m => m.MaganinCategoryMaganinModule),
      },
      {
        path: 'consultancy-maganin',
        loadChildren: () => import('./consultancy-maganin/consultancy-maganin.module').then(m => m.MaganinConsultancyMaganinModule),
      },
      {
        path: 'consultancy-type-maganin',
        loadChildren: () =>
          import('./consultancy-type-maganin/consultancy-type-maganin.module').then(m => m.MaganinConsultancyTypeMaganinModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MaganinEntityModule {}
