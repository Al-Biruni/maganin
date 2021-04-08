import { Component, Input, OnInit } from '@angular/core';
import { ArticleSummary } from '../../shared/model/article-summery.model';

@Component({
  selector: 'jhi-article-summery-list',
  templateUrl: './article-summery-list.component.html',
  styleUrls: ['./article-summery-list.component.scss'],
})
export class ArticleSummeryListComponent implements OnInit {
  @Input() articles: ArticleSummary[] | undefined = [];
  @Input() listTitle: string | undefined;

  constructor() {}

  ngOnInit(): void {}
}
