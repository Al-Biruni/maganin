import { Moment } from 'moment';

export interface IArticleMaganin {
  id?: number;
  userId?: number;
  title?: string;
  author?: string;
  relatedURL?: string;
  dateAdded?: Moment;
  shortDesc?: string;
  longDesc?: string;
  display?: string;
  accessLevel?: number;
  impressions?: number;
  avgRating?: number;
  thumbnail?: string;
  keywords?: string;
  country?: string;
  dateLastMod?: Moment;
  articleCategory?: string;
  writerImageURL?: string;
}

export class ArticleMaganin implements IArticleMaganin {
  constructor(
    public id?: number,
    public userId?: number,
    public title?: string,
    public author?: string,
    public relatedURL?: string,
    public dateAdded?: Moment,
    public shortDesc?: string,
    public longDesc?: string,
    public display?: string,
    public accessLevel?: number,
    public impressions?: number,
    public avgRating?: number,
    public thumbnail?: string,
    public keywords?: string,
    public country?: string,
    public dateLastMod?: Moment,
    public articleCategory?: string,
    public writerImageURL?: string
  ) {
    this.impressions = 0;
  }
}
