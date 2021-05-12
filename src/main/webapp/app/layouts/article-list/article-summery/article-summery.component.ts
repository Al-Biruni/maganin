import { Component, Input, OnInit } from '@angular/core';
import { IArticleMaganin } from 'app/shared/model/article-maganin.model';

@Component({
  selector: 'jhi-article-summery',
  templateUrl: './article-summery.component.html',
  styleUrls: ['./article-summery.component.scss'],
})
export class ArticleSummeryComponent implements OnInit {
  @Input() articleSummery?: IArticleMaganin;

  constructor() {}

  ngOnInit(): void {}
}
