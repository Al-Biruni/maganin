import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IContentMaganin, ContentMaganin } from 'app/shared/model/content-maganin.model';
import { ContentMaganinService } from './content-maganin.service';
import { ICategoryMaganin } from 'app/shared/model/category-maganin.model';
import { CategoryMaganinService } from 'app/entities/category-maganin/category-maganin.service';

@Component({
  selector: 'jhi-content-maganin-update',
  templateUrl: './content-maganin-update.component.html',
})
export class ContentMaganinUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategoryMaganin[] = [];

  editForm = this.fb.group({
    id: [],
    userId: [],
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
    categoryId: [],
  });

  constructor(
    protected contentService: ContentMaganinService,
    protected categoryService: CategoryMaganinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ content }) => {
      if (!content.id) {
        const today = moment().startOf('day');
        content.dateAdded = today;
        content.expire = today;
        content.dateEnd = today;
        content.dateLastMod = today;
      }

      this.updateForm(content);

      this.categoryService.query().subscribe((res: HttpResponse<ICategoryMaganin[]>) => (this.categories = res.body || []));
    });
  }

  updateForm(content: IContentMaganin): void {
    this.editForm.patchValue({
      id: content.id,
      userId: content.userId,
      title: content.title,
      author: content.author,
      relatedURL: content.relatedURL,
      dateAdded: content.dateAdded ? content.dateAdded.format(DATE_TIME_FORMAT) : null,
      shortDesc: content.shortDesc,
      longDesc: content.longDesc,
      display: content.display,
      accessLevel: content.accessLevel,
      expire: content.expire ? content.expire.format(DATE_TIME_FORMAT) : null,
      priority: content.priority,
      impressions: content.impressions,
      clickThrus: content.clickThrus,
      avgRating: content.avgRating,
      ratings: content.ratings,
      thumbnail: content.thumbnail,
      image1: content.image1,
      dateEnd: content.dateEnd ? content.dateEnd.format(DATE_TIME_FORMAT) : null,
      keywords: content.keywords,
      creationsType: content.creationsType,
      wazn: content.wazn,
      country: content.country,
      dateLastMod: content.dateLastMod ? content.dateLastMod.format(DATE_TIME_FORMAT) : null,
      categoryId: content.categoryId,
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

  private createFromForm(): IContentMaganin {
    return {
      ...new ContentMaganin(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      title: this.editForm.get(['title'])!.value,
      author: this.editForm.get(['author'])!.value,
      relatedURL: this.editForm.get(['relatedURL'])!.value,
      dateAdded: this.editForm.get(['dateAdded'])!.value ? moment(this.editForm.get(['dateAdded'])!.value, DATE_TIME_FORMAT) : undefined,
      shortDesc: this.editForm.get(['shortDesc'])!.value,
      longDesc: this.editForm.get(['longDesc'])!.value,
      display: this.editForm.get(['display'])!.value,
      accessLevel: this.editForm.get(['accessLevel'])!.value,
      expire: this.editForm.get(['expire'])!.value ? moment(this.editForm.get(['expire'])!.value, DATE_TIME_FORMAT) : undefined,
      priority: this.editForm.get(['priority'])!.value,
      impressions: this.editForm.get(['impressions'])!.value,
      clickThrus: this.editForm.get(['clickThrus'])!.value,
      avgRating: this.editForm.get(['avgRating'])!.value,
      ratings: this.editForm.get(['ratings'])!.value,
      thumbnail: this.editForm.get(['thumbnail'])!.value,
      image1: this.editForm.get(['image1'])!.value,
      dateEnd: this.editForm.get(['dateEnd'])!.value ? moment(this.editForm.get(['dateEnd'])!.value, DATE_TIME_FORMAT) : undefined,
      keywords: this.editForm.get(['keywords'])!.value,
      creationsType: this.editForm.get(['creationsType'])!.value,
      wazn: this.editForm.get(['wazn'])!.value,
      country: this.editForm.get(['country'])!.value,
      dateLastMod: this.editForm.get(['dateLastMod'])!.value
        ? moment(this.editForm.get(['dateLastMod'])!.value, DATE_TIME_FORMAT)
        : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContentMaganin>>): void {
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

  trackById(index: number, item: ICategoryMaganin): any {
    return item.id;
  }
}
