import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContentMaganin } from 'app/shared/model/content-maganin.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ContentMaganinService } from './content-maganin.service';
import { ContentMaganinDeleteDialogComponent } from './content-maganin-delete-dialog.component';

@Component({
  selector: 'jhi-content-maganin',
  templateUrl: './content-maganin.component.html',
})
export class ContentMaganinComponent implements OnInit, OnDestroy {
  contents: IContentMaganin[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected contentService: ContentMaganinService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.contents = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.contentService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IContentMaganin[]>) => this.paginateContents(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.contents = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContentMaganin): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContents(): void {
    this.eventSubscriber = this.eventManager.subscribe('contentListModification', () => this.reset());
  }

  delete(content: IContentMaganin): void {
    const modalRef = this.modalService.open(ContentMaganinDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.content = content;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateContents(data: IContentMaganin[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.contents.push(data[i]);
      }
    }
  }
}
