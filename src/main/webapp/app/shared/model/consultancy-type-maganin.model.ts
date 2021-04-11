export interface IConsultancyTypeMaganin {
  id?: number;
  type?: string;
}

export class ConsultancyTypeMaganin implements IConsultancyTypeMaganin {
  constructor(public id?: number, public type?: string) {}
}
