import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategoryMaganin, CategoryMaganin } from 'app/shared/model/category-maganin.model';
import { CategoryMaganinService } from './category-maganin.service';

@Component({
  selector: 'jhi-category-maganin-update',
  templateUrl: './category-maganin-update.component.html',
})
export class CategoryMaganinUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    categoryTypeId: [],
    categoryName: [],
    parentId: [],
    description: [],
    imageURL: [],
    hideSearch: [],
  });

  constructor(protected categoryService: CategoryMaganinService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ category }) => {
      this.updateForm(category);
    });
  }

  updateForm(category: ICategoryMaganin): void {
    this.editForm.patchValue({
      id: category.id,
      categoryTypeId: category.categoryTypeId,
      categoryName: category.categoryName,
      parentId: category.parentId,
      description: category.description,
      imageURL: category.imageURL,
      hideSearch: category.hideSearch,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategoryMaganin {
    return {
      ...new CategoryMaganin(),
      id: this.editForm.get(['id'])!.value,
      categoryTypeId: this.editForm.get(['categoryTypeId'])!.value,
      categoryName: this.editForm.get(['categoryName'])!.value,
      parentId: this.editForm.get(['parentId'])!.value,
      description: this.editForm.get(['description'])!.value,
      imageURL: this.editForm.get(['imageURL'])!.value,
      hideSearch: this.editForm.get(['hideSearch'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoryMaganin>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
