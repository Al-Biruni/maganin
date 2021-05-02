import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IConsultancyMaganin, ConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';
import { ConsultancyMaganinService } from './consultancy-maganin.service';
import { IConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';
import { ConsultancyTypeMaganinService } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin.service';

@Component({
  selector: 'jhi-consultancy-maganin-update',
  templateUrl: './consultancy-maganin-update.component.html',
})
export class ConsultancyMaganinUpdateComponent implements OnInit {
  isSaving = false;
  consultancyTypes: IConsultancyTypeMaganin[] = [];

  editForm = this.fb.group({
    id: [],
    userId: [],
    name: [],
    date: [],
    age: [],
    gender: [],
    religion: [],
    rel2: [],
    edu: [],
    social: [],
    econ: [],
    hop: [],
    job: [],
    country: [],
    origin: [],
    email: [],
    title: [],
    question: [],
    answer: [],
    doctor: [],
    consultantId: [],
    show: [],
    paid: [],
    impressions: [],
    consultancyType: [],
  });

  constructor(
    protected consultancyService: ConsultancyMaganinService,
    protected consultancyTypeService: ConsultancyTypeMaganinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ consultancy }) => {
      if (!consultancy.id) {
        const today = moment().startOf('day');
        consultancy.date = today;
      }

      this.updateForm(consultancy);

      this.consultancyTypeService
        .query()
        .subscribe((res: HttpResponse<IConsultancyTypeMaganin[]>) => (this.consultancyTypes = res.body || []));
    });
  }

  updateForm(consultancy: IConsultancyMaganin): void {
    this.editForm.patchValue({
      id: consultancy.id,
      userId: consultancy.userId,
      name: consultancy.name,
      date: consultancy.date ? consultancy.date.format(DATE_TIME_FORMAT) : null,
      age: consultancy.age,
      gender: consultancy.gender,
      religion: consultancy.religion,
      rel2: consultancy.rel2,
      edu: consultancy.edu,
      social: consultancy.social,
      econ: consultancy.econ,
      hop: consultancy.hop,
      job: consultancy.job,
      country: consultancy.country,
      origin: consultancy.origin,
      email: consultancy.email,
      title: consultancy.title,
      question: consultancy.question,
      answer: consultancy.answer,
      doctor: consultancy.doctor,
      consultantId: consultancy.consultantId,
      show: consultancy.show,
      paid: consultancy.paid,
      impressions: consultancy.impressions,
      consultancyType: consultancy.consultancyType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const consultancy = this.createFromForm();
    if (consultancy.id !== undefined) {
      this.subscribeToSaveResponse(this.consultancyService.update(consultancy));
    } else {
      this.subscribeToSaveResponse(this.consultancyService.create(consultancy));
    }
  }

  private createFromForm(): IConsultancyMaganin {
    return {
      ...new ConsultancyMaganin(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      name: this.editForm.get(['name'])!.value,
      date: this.editForm.get(['date'])!.value ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      age: this.editForm.get(['age'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      religion: this.editForm.get(['religion'])!.value,
      rel2: this.editForm.get(['rel2'])!.value,
      edu: this.editForm.get(['edu'])!.value,
      social: this.editForm.get(['social'])!.value,
      econ: this.editForm.get(['econ'])!.value,
      hop: this.editForm.get(['hop'])!.value,
      job: this.editForm.get(['job'])!.value,
      country: this.editForm.get(['country'])!.value,
      origin: this.editForm.get(['origin'])!.value,
      email: this.editForm.get(['email'])!.value,
      title: this.editForm.get(['title'])!.value,
      question: this.editForm.get(['question'])!.value,
      answer: this.editForm.get(['answer'])!.value,
      doctor: this.editForm.get(['doctor'])!.value,
      consultantId: this.editForm.get(['consultantId'])!.value,
      show: this.editForm.get(['show'])!.value,
      consultancyType: this.editForm.get(['consultancyType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConsultancyMaganin>>): void {
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

  trackById(index: number, item: IConsultancyTypeMaganin): any {
    return item.id;
  }
}
