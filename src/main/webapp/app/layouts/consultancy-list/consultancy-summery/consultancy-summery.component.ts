import { Component, Input, OnInit } from '@angular/core';
import { ConsultancySummery } from '../../../shared/model/consultancy-summery.model';

@Component({
  selector: 'jhi-consultancy-summery',
  templateUrl: './consultancy-summery.component.html',
  styleUrls: ['./consultancy-summery.component.scss'],
})
export class ConsultancySummeryComponent implements OnInit {
  @Input() consultancySummery?: ConsultancySummery;

  constructor() {}

  ngOnInit(): void {}
}
