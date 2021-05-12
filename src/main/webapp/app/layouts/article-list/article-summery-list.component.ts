import { Component, Input, OnInit } from '@angular/core';
import { IArticleMaganin } from 'app/shared/model/article-maganin.model';

@Component({
  selector: 'jhi-article-summery-list',
  templateUrl: './article-summery-list.component.html',
  styleUrls: ['./article-summery-list.component.scss'],
})
export class ArticleSummeryListComponent implements OnInit {
  @Input() articles?: IArticleMaganin[] = [];
  @Input() listTitle?: string;

  constructor() {}

  ngOnInit(): void {}
}
