import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { NgxEditorModule } from 'ngx-editor';
import { MaganinSharedLibsModule } from 'app/shared/shared-libs.module';
import { MaganinEditorComponent } from 'app/shared/htmlEditor/editor-maganin.component';

@NgModule({
  imports: [
    FormsModule,
    NgxEditorModule.forRoot({
      locals: {
        // menu
        bold: 'Bold',
        italic: 'Italic',
        code: 'Code',
        blockquote: 'Blockquote',
        underline: 'Underline',
        strike: 'Strike',
        bulletList: 'Bullet List',
        orderedList: 'Ordered List',
        heading: 'Heading',
        h1: 'Header 1',
        h2: 'Header 2',
        h3: 'Header 3',
        h4: 'Header 4',
        h5: 'Header 5',
        h6: 'Header 6',
        alignLeft: 'Left Align',
        alignCenter: 'Center Align',
        alignRight: 'Right Align',
        alignJustify: 'Justify',
        textColor: 'Text Color',
        backgroundColor: 'Background Color',

        // popups, forms, others...
        url: 'URL',
        text: 'Text',
        openInNewTab: 'Open in new tab',
        insert: 'Insert',
        altText: 'Alt Text',
        title: 'Title',
        remove: 'Remove',
      },
    }),
    MaganinSharedLibsModule,
  ],
  declarations: [MaganinEditorComponent],
  exports: [MaganinEditorComponent],
})
export class MaganinEditorModule {}
