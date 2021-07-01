import { Component, Input, OnInit } from '@angular/core';
import { Doctor } from 'app/shared/model/doctor.model';

@Component({
  selector: 'jhi-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.scss'],
})
export class DoctorListComponent implements OnInit {
  @Input() doctors?: Doctor[] = [];

  constructor() {}

  ngOnInit(): void {}
}
