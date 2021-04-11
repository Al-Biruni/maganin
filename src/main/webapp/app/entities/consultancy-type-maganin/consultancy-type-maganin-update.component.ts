import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConsultancyTypeMaganin, ConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';
import { ConsultancyTypeMaganinService } from './consultancy-type-maganin.service';

@Component({
  selector: 'jhi-consultancy-type-maganin-update',
  templateUrl: './consultancy-type-maganin-update.component.html',
})
export class ConsultancyTypeMaganinUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    type: [],
  });

  constructor(
    protected consultancyTypeService: ConsultancyTypeMaganinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ consultancyType }) => {
      this.updateForm(consultancyType);
    });
  }

  updateForm(consultancyType: IConsultancyTypeMaganin): void {
    this.editForm.patchValue({
      id: consultancyType.id,
      type: consultancyType.type,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const consultancyType = this.createFromForm();
    if (consultancyType.id !== undefined) {
      this.subscribeToSaveResponse(this.consultancyTypeService.update(consultancyType));
    } else {
      this.subscribeToSaveResponse(this.consultancyTypeService.create(consultancyType));
    }
  }

  private createFromForm(): IConsultancyTypeMaganin {
    return {
      ...new ConsultancyTypeMaganin(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConsultancyTypeMaganin>>): void {
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
