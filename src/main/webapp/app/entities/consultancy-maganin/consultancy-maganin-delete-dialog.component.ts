import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';
import { ConsultancyMaganinService } from './consultancy-maganin.service';

@Component({
  templateUrl: './consultancy-maganin-delete-dialog.component.html',
})
export class ConsultancyMaganinDeleteDialogComponent {
  consultancy?: IConsultancyMaganin;

  constructor(
    protected consultancyService: ConsultancyMaganinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.consultancyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('consultancyListModification');
      this.activeModal.close();
    });
  }
}
