import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaganinSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ArticleSummeryListModule } from 'app/layouts/article-summery-list/article-summery-list.module';

@NgModule({
  imports: [MaganinSharedModule, RouterModule.forChild([HOME_ROUTE]), ArticleSummeryListModule],
  declarations: [HomeComponent],
})
export class MaganinHomeModule {}
