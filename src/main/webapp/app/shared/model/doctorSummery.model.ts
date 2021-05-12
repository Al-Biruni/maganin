export interface IDoctorDTO {
  doctorId?: number;
  doctorName?: string;
}

export class doctorDtO implements IDoctorDTO {
  constructor(public doctorId?: number, public doctorName?: string) {}
}
