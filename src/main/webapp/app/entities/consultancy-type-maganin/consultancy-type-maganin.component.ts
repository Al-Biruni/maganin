import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';
import { ConsultancyTypeMaganinService } from './consultancy-type-maganin.service';
import { ConsultancyTypeMaganinDeleteDialogComponent } from './consultancy-type-maganin-delete-dialog.component';

@Component({
  selector: 'jhi-consultancy-type-maganin',
  templateUrl: './consultancy-type-maganin.component.html',
})
export class ConsultancyTypeMaganinComponent implements OnInit, OnDestroy {
  consultancyTypes?: IConsultancyTypeMaganin[];
  eventSubscriber?: Subscription;

  constructor(
    protected consultancyTypeService: ConsultancyTypeMaganinService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.consultancyTypeService
      .query()
      .subscribe((res: HttpResponse<IConsultancyTypeMaganin[]>) => (this.consultancyTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConsultancyTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConsultancyTypeMaganin): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConsultancyTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('consultancyTypeListModification', () => this.loadAll());
  }

  delete(consultancyType: IConsultancyTypeMaganin): void {
    const modalRef = this.modalService.open(ConsultancyTypeMaganinDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.consultancyType = consultancyType;
  }
}
