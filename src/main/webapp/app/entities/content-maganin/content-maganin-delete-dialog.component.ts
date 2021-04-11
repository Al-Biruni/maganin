import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContentMaganin } from 'app/shared/model/content-maganin.model';
import { ContentMaganinService } from './content-maganin.service';

@Component({
  templateUrl: './content-maganin-delete-dialog.component.html',
})
export class ContentMaganinDeleteDialogComponent {
  content?: IContentMaganin;

  constructor(
    protected contentService: ContentMaganinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contentListModification');
      this.activeModal.close();
    });
  }
}
