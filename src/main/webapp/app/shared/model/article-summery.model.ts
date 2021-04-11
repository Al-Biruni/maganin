export interface IArticleSummary {
  id: number;
  title: string;
  author: string;
  shortDesc: string;
  impressions: number;
  avgRating: number;
  thumbnail: string;
  writerImageURL: string;
  dateLastMod: string;
  articleCategory: string;
}

export class ArticleSummary implements IArticleSummary {
  constructor(
    public id: number,
    public title: string,
    public author: string,
    public shortDesc: string,
    public impressions: number,
    public avgRating: number,
    public writerImageURL: string,
    public thumbnail: string,
    public dateLastMod: string,
    public articleCategory: string
  ) {
    this.impressions = 0;
    this.dateLastMod = '';
  }
}
