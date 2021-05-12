export interface IConsultancyTypeMaganin {
  id?: number;
  arName?: string;
}

export class ConsultancyTypeMaganin implements IConsultancyTypeMaganin {
  constructor(public id?: number, public arName?: string) {}
}
