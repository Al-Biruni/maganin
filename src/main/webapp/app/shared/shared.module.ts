import { NgModule } from '@angular/core';
import { DukesdesignsSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { ArabicNumberPipe } from 'app/shared/pipe/arNumber.pipe';

@NgModule({
  imports: [DukesdesignsSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    ArabicNumberPipe,
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    DukesdesignsSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    ArabicNumberPipe,
  ],
})
export class DukesdesignsSharedModule {}
