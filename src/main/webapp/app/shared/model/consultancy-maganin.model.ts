import { Moment } from 'moment';

export interface IConsultancyMaganin {
  id?: number;
  userId?: number;
  name?: string;
  date?: Moment;
  age?: string;
  gender?: string;
  religion?: string;
  rel2?: string;
  edu?: string;
  social?: string;
  econ?: string;
  hop?: string;
  job?: string;
  country?: string;
  origin?: string;
  email?: string;
  title?: string;
  question?: string;
  answer?: string;
  doctor?: string;
  consultantId?: number;
  show?: boolean;
  paid?: boolean;
  impressions?: number;
  consultancyType?: String;
}

export class ConsultancyMaganin implements IConsultancyMaganin {
  constructor(
    public id?: number,
    public userId?: number,
    public name?: string,
    public date?: Moment,
    public age?: string,
    public gender?: string,
    public religion?: string,
    public rel2?: string,
    public edu?: string,
    public social?: string,
    public econ?: string,
    public hop?: string,
    public job?: string,
    public country?: string,
    public origin?: string,
    public email?: string,
    public title?: string,
    public question?: string,
    public answer?: string,
    public doctor?: string,
    public consultantId?: number,
    public show?: boolean,
    public paid?: boolean,
    public impressions?: number,
    public consultancyType?: String
  ) {
    this.show = this.show || false;
    this.paid = this.paid || false;
  }
}
