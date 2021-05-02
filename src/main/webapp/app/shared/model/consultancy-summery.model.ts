import { Moment } from 'moment';
import { Doctor, IDoctorMaganin } from './doctor.model';
import { ConsultancyTypeMaganin, IConsultancyTypeMaganin } from './consultancy-type-maganin.model';

export interface IConsultancySummery {
  id?: number;
  date?: Moment;
  title?: string;
  question?: string;
  doctor?: IDoctorMaganin;
  impressions?: number;
  consultancyType?: string;
}

export class ConsultancySummery implements IConsultancySummery {
  constructor(
    public id?: number,
    public date?: Moment,
    public title?: string,
    public question?: string,
    public doctor?: Doctor,
    public impressions?: number,
    public consultancyType?: string
  ) {}
}
