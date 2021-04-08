import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContent, Content } from 'app/shared/model/content.model';
import { ContentService } from './content.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';

@Component({
  selector: 'jhi-content-update',
  templateUrl: './content-update.component.html',
})
export class ContentUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategory[] = [];
  dateAddedDp: any;
  expireDp: any;
  dateEndDp: any;
  dateLastModDp: any;

  editForm = this.fb.group({
    id: [],
    userId: [],
    contentTypeID: [],
    title: [],
    author: [],
    relatedURL: [],
    dateAdded: [],
    shortDesc: [],
    longDesc: [],
    display: [],
    accessLevel: [],
    expire: [],
    priority: [],
    impressions: [],
    clickThrus: [],
    avgRating: [],
    ratings: [],
    thumbnail: [],
    image1: [],
    dateEnd: [],
    keywords: [],
    creationsType: [],
    wazn: [],
    country: [],
    dateLastMod: [],
    categoryID: [],
  });

  constructor(
    protected contentService: ContentService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ content }) => {
      this.updateForm(content);

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));
    });
  }

  updateForm(content: IContent): void {
    this.editForm.patchValue({
      id: content.id,
      userId: content.userId,
      contentTypeID: content.contentTypeID,
      title: content.title,
      author: content.author,
      relatedURL: content.relatedURL,
      dateAdded: content.dateAdded,
      shortDesc: content.shortDesc,
      longDesc: content.longDesc,
      display: content.display,
      accessLevel: content.accessLevel,
      expire: content.expire,
      priority: content.priority,
      impressions: content.impressions,
      clickThrus: content.clickThrus,
      avgRating: content.avgRating,
      ratings: content.ratings,
      thumbnail: content.thumbnail,
      image1: content.image1,
      dateEnd: content.dateEnd,
      keywords: content.keywords,
      creationsType: content.creationsType,
      wazn: content.wazn,
      country: content.country,
      dateLastMod: content.dateLastMod,
      categoryID: content.categoryID,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const content = this.createFromForm();
    if (content.id !== undefined) {
      this.subscribeToSaveResponse(this.contentService.update(content));
    } else {
      this.subscribeToSaveResponse(this.contentService.create(content));
    }
  }

  private createFromForm(): IContent {
    return {
      ...new Content(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      contentTypeID: this.editForm.get(['contentTypeID'])!.value,
      title: this.editForm.get(['title'])!.value,
      author: this.editForm.get(['author'])!.value,
      relatedURL: this.editForm.get(['relatedURL'])!.value,
      dateAdded: this.editForm.get(['dateAdded'])!.value,
      shortDesc: this.editForm.get(['shortDesc'])!.value,
      longDesc: this.editForm.get(['longDesc'])!.value,
      display: this.editForm.get(['display'])!.value,
      accessLevel: this.editForm.get(['accessLevel'])!.value,
      expire: this.editForm.get(['expire'])!.value,
      priority: this.editForm.get(['priority'])!.value,
      impressions: this.editForm.get(['impressions'])!.value,
      clickThrus: this.editForm.get(['clickThrus'])!.value,
      avgRating: this.editForm.get(['avgRating'])!.value,
      ratings: this.editForm.get(['ratings'])!.value,
      thumbnail: this.editForm.get(['thumbnail'])!.value,
      image1: this.editForm.get(['image1'])!.value,
      dateEnd: this.editForm.get(['dateEnd'])!.value,
      keywords: this.editForm.get(['keywords'])!.value,
      creationsType: this.editForm.get(['creationsType'])!.value,
      wazn: this.editForm.get(['wazn'])!.value,
      country: this.editForm.get(['country'])!.value,
      dateLastMod: this.editForm.get(['dateLastMod'])!.value,
      categoryID: this.editForm.get(['categoryID'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContent>>): void {
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

  trackById(index: number, item: ICategory): any {
    return item.id;
  }
}
