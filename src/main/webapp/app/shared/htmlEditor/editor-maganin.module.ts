import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgxEditorModule } from 'ngx-editor';
import { MaganinSharedLibsModule } from 'app/shared/shared-libs.module';
import { MaganinEditorComponent } from 'app/shared/htmlEditor/editor-maganin.component';
import { SafeHtmlPipe } from '../pipe/safeHTML.pipe';

@NgModule({
  imports: [FormsModule, NgxEditorModule, MaganinSharedLibsModule],
  declarations: [MaganinEditorComponent],
  exports: [MaganinEditorComponent],
  providers: [SafeHtmlPipe],
})
export class MaganinEditorModule {}
