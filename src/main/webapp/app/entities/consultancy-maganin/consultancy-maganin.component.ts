import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ConsultancyMaganinService } from './consultancy-maganin.service';
import { ConsultancyMaganinDeleteDialogComponent } from './consultancy-maganin-delete-dialog.component';
import { IConsultancySummery } from '../../shared/model/consultancy-summery.model';
import { AccountService } from '../../core/auth/account.service';

@Component({
  selector: 'jhi-consultancy-maganin',
  templateUrl: './consultancy-maganin.component.html',
  styleUrls: ['./consultancy-maganin.component.scss'],
})
export class ConsultancyMaganinComponent implements OnInit, OnDestroy {
  consultancies!: IConsultancySummery[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  impressionSort = true;

  constructor(
    protected accountService: AccountService,
    protected consultancyService: ConsultancyMaganinService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;
    if (this.isAdmin()) {
      this.consultancyService
        .query({
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe(
          (res: HttpResponse<IConsultancySummery[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
          () => this.onError()
        );
    } else {
      this.consultancyService
        .queryPublished({
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe(
          (res: HttpResponse<IConsultancySummery[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
          () => this.onError()
        );
    }
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInConsultancies();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConsultancySummery): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConsultancies(): void {
    this.eventSubscriber = this.eventManager.subscribe('consultancyListModification', () => this.loadPage());
  }

  delete(consultancy: IConsultancySummery): void {
    const modalRef = this.modalService.open(ConsultancyMaganinDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.consultancy = consultancy;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IConsultancySummery[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/consultancy-maganin'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.consultancies = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
  isAdmin(): boolean {
    return this.accountService.hasAnyAuthority('ROLE_ADMIN');
  }
  sortByImpressions(): void {
    this?.consultancies.sort((a, b) => {
      if (this.impressionSort) {
        return b?.impressions - a?.impressions;
      } else {
        return a?.impressions - b?.impressions;
      }
    });
    this.registerChangeInConsultancies();
    this.impressionSort = !this.impressionSort;
  }
}
