import { Moment } from 'moment';

export interface IConsultancySummery {
  id: number;
  dateAdded?: Moment;
  title?: string;
  question?: string;
  doctor?: number;
  impressions: number;
  consultancyType?: number;
}

export class ConsultancySummery implements IConsultancySummery {
  constructor(
    public id: number,
    public impressions: number,
    public dateAdded?: Moment,
    public title?: string,
    public question?: string,
    public doctor?: number,
    public consultancyType?: number
  ) {}
}
