import { Component, Input, OnInit } from '@angular/core';
import { Doctor } from 'app/shared/model/doctor.model';

@Component({
  selector: 'jhi-doctor-card-summery',
  templateUrl: './doctor-card-summery.component.html',
  styleUrls: ['./doctor-card-summery.component.scss'],
})
export class DoctorCardSummeryComponent implements OnInit {
  @Input() doctor?: Doctor;
  constructor() {}

  ngOnInit(): void {}
}
