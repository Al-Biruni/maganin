package com.digitalraider.maganin.service.dto;

import com.digitalraider.maganin.domain.Doctor;

public class DoctorDTO {
    public String doctorName;
    public Integer doctorId;

    public DoctorDTO(Doctor doctor){
        this.doctorId = doctor.id;
        this.doctorName = doctor.name;
    }

}
