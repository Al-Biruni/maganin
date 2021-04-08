import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import localeAr from '@angular/common/locales/ar-EG';
import { registerLocaleData } from '@angular/common';

import './vendor';
import { DukesdesignsSharedModule } from 'app/shared/shared.module';
import { DukesdesignsCoreModule } from 'app/core/core.module';
import { DukesdesignsAppRoutingModule } from './app-routing.module';
import { DukesdesignsHomeModule } from './home/home.module';
import { DukesdesignsEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

registerLocaleData(localeAr);
@NgModule({
  imports: [
    BrowserModule,
    DukesdesignsSharedModule,
    DukesdesignsCoreModule,
    DukesdesignsHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    DukesdesignsEntityModule,
    DukesdesignsAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class DukesdesignsAppModule {}
