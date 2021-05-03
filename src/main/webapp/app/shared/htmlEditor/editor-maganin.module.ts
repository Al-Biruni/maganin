import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgxEditorModule } from 'ngx-editor';
import { MaganinSharedLibsModule } from 'app/shared/shared-libs.module';
import { MaganinEditorComponent } from 'app/shared/htmlEditor/editor-maganin.component';

@NgModule({
  imports: [FormsModule, NgxEditorModule, MaganinSharedLibsModule],
  declarations: [MaganinEditorComponent],
  exports: [MaganinEditorComponent],
})
export class MaganinEditorModule {}
