import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoryMaganin } from 'app/shared/model/category-maganin.model';
import { CategoryMaganinService } from './category-maganin.service';

@Component({
  templateUrl: './category-maganin-delete-dialog.component.html',
})
export class CategoryMaganinDeleteDialogComponent {
  category?: ICategoryMaganin;

  constructor(
    protected categoryService: CategoryMaganinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categoryListModification');
      this.activeModal.close();
    });
  }
}
