import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsultancyMaganin } from 'app/shared/model/consultancy-maganin.model';

@Component({
  selector: 'jhi-consultancy-maganin-detail',
  templateUrl: './consultancy-maganin-detail.component.html',
})
export class ConsultancyMaganinDetailComponent implements OnInit {
  consultancy: IConsultancyMaganin | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ consultancy }) => (this.consultancy = consultancy));
  }

  previousState(): void {
    window.history.back();
  }
}
