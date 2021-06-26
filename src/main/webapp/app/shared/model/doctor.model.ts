import { Moment } from 'moment';

export interface IDoctorMaganin {
  id?: number;
  userId?: number;
  name?: string;
  dateAdded?: Moment;
  shortDesc?: string;
  longDesc?: string;
  impressions?: number;
  avgRating?: number;
  thumbnail?: string;
}

export class Doctor implements IDoctorMaganin {
  constructor(
    public id?: number,
    public userId?: number,
    public name?: string,
    public dateAdded?: Moment,
    public shortDesc?: string,
    public longDesc?: string,
    public impressions?: number,
    public avgRating?: number,
    public thumbnail?: string
  ) {}
}
