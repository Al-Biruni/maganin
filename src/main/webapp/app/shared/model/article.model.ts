import { Moment } from 'moment';

export interface IArticle {
  id?: number;
  title?: string;
  author?: string;
  authorPageURL?: string;
  shortDesc?: string;
  longDesc?: string;
  impressions?: number;
  avgRating?: number;
  thumbnail?: string;
  writerImageURL?: string;
  keywords?: string;
  dateLastMod?: Moment;
  articleCategory?: string;
}

export class Article implements IArticle {
  constructor(
    public id?: number,
    public title?: string,
    public author?: string,
    public authorPageURL?: string,
    public shortDesc?: string,
    public longDesc?: string,
    public impressions?: number,
    public avgRating?: number,
    public thumbnail?: string,
    public writerImageURL?: string,
    public keywords?: string,
    public dateLastMod?: Moment,
    public articleCategory?: string
  ) {}
}
