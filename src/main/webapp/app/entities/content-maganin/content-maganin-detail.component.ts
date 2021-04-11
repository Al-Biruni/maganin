import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContentMaganin } from 'app/shared/model/content-maganin.model';

@Component({
  selector: 'jhi-content-maganin-detail',
  templateUrl: './content-maganin-detail.component.html',
})
export class ContentMaganinDetailComponent implements OnInit {
  content: IContentMaganin | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ content }) => (this.content = content));
  }

  previousState(): void {
    window.history.back();
  }
}
