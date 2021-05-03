import { NgModule } from '@angular/core';
import { MaganinSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { ArabicNumberPipe } from 'app/shared/pipe/arNumber.pipe';
import { SafeHtmlPipe } from 'app/shared/pipe/safeHTML.pipe';

@NgModule({
  imports: [MaganinSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    ArabicNumberPipe,
    SafeHtmlPipe,
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    MaganinSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    ArabicNumberPipe,
    SafeHtmlPipe,
  ],
})
export class MaganinSharedModule {}
