import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';
import { ConsultancyTypeMaganinService } from './consultancy-type-maganin.service';

@Component({
  templateUrl: './consultancy-type-maganin-delete-dialog.component.html',
})
export class ConsultancyTypeMaganinDeleteDialogComponent {
  consultancyType?: IConsultancyTypeMaganin;

  constructor(
    protected consultancyTypeService: ConsultancyTypeMaganinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.consultancyTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('consultancyTypeListModification');
      this.activeModal.close();
    });
  }
}
