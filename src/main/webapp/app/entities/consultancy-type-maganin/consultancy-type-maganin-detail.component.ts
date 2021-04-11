import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsultancyTypeMaganin } from 'app/shared/model/consultancy-type-maganin.model';

@Component({
  selector: 'jhi-consultancy-type-maganin-detail',
  templateUrl: './consultancy-type-maganin-detail.component.html',
})
export class ConsultancyTypeMaganinDetailComponent implements OnInit {
  consultancyType: IConsultancyTypeMaganin | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ consultancyType }) => (this.consultancyType = consultancyType));
  }

  previousState(): void {
    window.history.back();
  }
}
