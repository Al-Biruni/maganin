import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryMaganin } from 'app/shared/model/category-maganin.model';

@Component({
  selector: 'jhi-category-maganin-detail',
  templateUrl: './category-maganin-detail.component.html',
})
export class CategoryMaganinDetailComponent implements OnInit {
  category: ICategoryMaganin | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ category }) => (this.category = category));
  }

  previousState(): void {
    window.history.back();
  }
}
