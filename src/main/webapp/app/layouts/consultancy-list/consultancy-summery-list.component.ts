import { Component, Input, OnInit } from '@angular/core';
import { ConsultancySummery } from '../../shared/model/consultancy-summery.model';

@Component({
  selector: 'jhi-consultancy-summery-list',
  templateUrl: './consultancy-summery-list.component.html',
  styleUrls: ['./consultancy-summery-list.component.scss'],
})
export class ConsultancySummeryListComponent implements OnInit {
  @Input() consultancies?: ConsultancySummery[] = [];
  @Input() listTitle?: string;

  constructor() {}

  ngOnInit(): void {}
}
