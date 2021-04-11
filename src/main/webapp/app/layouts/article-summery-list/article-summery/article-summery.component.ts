import { Component, Input, OnInit } from '@angular/core';
import { ArticleSummary } from '../../../shared/model/article-summery.model';

@Component({
  selector: 'jhi-article-summery',
  templateUrl: './article-summery.component.html',
  styleUrls: ['./article-summery.component.scss'],
})
export class ArticleSummeryComponent implements OnInit {
  @Input() articleSummery: ArticleSummary | undefined;

  constructor() {}

  ngOnInit(): void {}
}
