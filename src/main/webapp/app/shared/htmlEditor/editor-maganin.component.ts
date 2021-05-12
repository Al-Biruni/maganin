import { Component, Input, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';

import { Editor, Toolbar } from 'ngx-editor';
import { FormGroup } from '@angular/forms';
import schema from './magninEditorSchema';

@Component({
  selector: 'jhi-maganin-editor',
  template: `
    <div *ngIf="formGroup" [formGroup]="formGroup">
      <div class="NgxEditor__Wrapper">
        <ngx-editor-menu [editor]="editor" [toolbar]="toolbar" [colorPresets]="colorPresets"> </ngx-editor-menu>
        <ngx-editor [editor]="editor" [formControlName]="content"> </ngx-editor>
        <!--        <ngx-editor [editor]="editor" [formControlName]="content" [(ngModel)]="safeHtmlContent"> </ngx-editor>-->
      </div>
    </div>
  `,
  styles: [
    `
      ngx-editor {
        padding: 0;
      }
    `,
  ],
  encapsulation: ViewEncapsulation.Emulated,
})
export class MaganinEditorComponent implements OnInit, OnDestroy {
  @Input() content = '';
  @Input() formGroup?: FormGroup;

  editor: Editor;
  toolbar: Toolbar = [
    ['bold', 'italic'],
    ['underline', 'strike'],
    ['code', 'blockquote'],
    ['ordered_list', 'bullet_list'],
    [{ heading: ['h1', 'h2', 'h3', 'h4', 'h5', 'h6'] }],
    ['link', 'image'],
    ['text_color', 'background_color'],
    ['align_left', 'align_center', 'align_right', 'align_justify'],
  ];
  colorPresets = ['#cc0066', '#000000', '#FFFFFF'];
  constructor() {
    this.editor = new Editor({
      schema,
      history: true,
      keyboardShortcuts: true,
    });
  }

  ngOnInit(): void {
    // this.jsonDoc = toDoc(this.safeHtmlContent.toString())
    // this.editor.setContent(this.jsonDoc);
  }

  ngOnDestroy(): void {
    this.editor.destroy();
  }
}
