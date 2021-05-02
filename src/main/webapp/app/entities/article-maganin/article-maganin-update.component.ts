import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IArticleMaganin, ArticleMaganin } from 'app/shared/model/article-maganin.model';
import { ArticleMaganinService } from './article-maganin.service';
@Component({
  selector: 'jhi-article-maganin-update',
  templateUrl: './article-maganin-update.component.html',
})
export class ArticleMaganinUpdateComponent implements OnInit {
  isSaving = false;
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
    impressions: [],
    avgRating: [],
    thumbnail: [],
    keywords: [],
    country: [],
    dateLastMod: [],
  });

  constructor(protected articleService: ArticleMaganinService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ article }) => {
      if (!article.id) {
        const today = moment().startOf('day');
        article.dateAdded = today;
        article.dateLastMod = today;
      }

      this.updateForm(article);
    });
  }

  updateForm(article: IArticleMaganin): void {
    this.editForm.patchValue({
      id: article.id,
      userId: article.userId,
      title: article.title,
      author: article.author,
      relatedURL: article.relatedURL,
      shortDesc: article.shortDesc,
      longDesc: article.longDesc,
      display: article.display,
      accessLevel: article.accessLevel,
      avgRating: article.avgRating,
      thumbnail: article.thumbnail,
      keywords: article.keywords,
      country: article.country,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const article = this.createFromForm();
    if (article.id !== undefined) {
      this.subscribeToSaveResponse(this.articleService.update(article));
    } else {
      this.subscribeToSaveResponse(this.articleService.create(article));
    }
  }

  private createFromForm(): IArticleMaganin {
    return {
      ...new ArticleMaganin(),
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
      impressions: this.editForm.get(['impressions'])!.value,
      avgRating: this.editForm.get(['avgRating'])!.value,
      thumbnail: this.editForm.get(['thumbnail'])!.value,
      keywords: this.editForm.get(['keywords'])!.value,
      country: this.editForm.get(['country'])!.value,
      dateLastMod: this.editForm.get(['dateLastMod'])!.value
        ? moment(this.editForm.get(['dateLastMod'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IArticleMaganin>>): void {
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
