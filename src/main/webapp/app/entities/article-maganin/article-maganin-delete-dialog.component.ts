import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IArticleMaganin } from 'app/shared/model/article-maganin.model';
import { ArticleMaganinService } from './article-maganin.service';

@Component({
  templateUrl: './article-maganin-delete-dialog.component.html',
})
export class ArticleMaganinDeleteDialogComponent {
  article?: IArticleMaganin;

  constructor(
    protected articleService: ArticleMaganinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.articleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('articleListModification');
      this.activeModal.close();
    });
  }
}
