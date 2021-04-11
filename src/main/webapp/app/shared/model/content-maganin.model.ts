import { Moment } from 'moment';
import { ICategoryMaganin } from 'app/shared/model/category-maganin.model';

export interface IContentMaganin {
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
  expire?: Moment;
  priority?: number;
  impressions?: number;
  clickThrus?: number;
  avgRating?: number;
  ratings?: number;
  thumbnail?: string;
  image1?: string;
  dateEnd?: Moment;
  keywords?: string;
  creationsType?: string;
  wazn?: string;
  country?: string;
  dateLastMod?: Moment;
  categoryId?: ICategoryMaganin;
}

export class ContentMaganin implements IContentMaganin {
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
    public expire?: Moment,
    public priority?: number,
    public impressions?: number,
    public clickThrus?: number,
    public avgRating?: number,
    public ratings?: number,
    public thumbnail?: string,
    public image1?: string,
    public dateEnd?: Moment,
    public keywords?: string,
    public creationsType?: string,
    public wazn?: string,
    public country?: string,
    public dateLastMod?: Moment,
    public categoryId?: ICategoryMaganin
  ) {}
}
