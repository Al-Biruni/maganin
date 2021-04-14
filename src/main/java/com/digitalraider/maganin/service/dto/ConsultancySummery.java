package com.digitalraider.maganin.service.dto;


import com.digitalraider.maganin.domain.Consultancy;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ConsultancySummery {


    public Long id;
    public LocalDateTime dateAdded;
    public String title;
    public String question;
    public String doctor;
    public Integer impressions;
    public String consultancyType;

    public ConsultancySummery(Consultancy c){
        this.id = c.id;
        this.dateAdded =  LocalDateTime.ofInstant(c.date, ZoneOffset.UTC);
        this.title= c.title;
        this.question = c.question;
        this.doctor= c.doctor;
        this.impressions= c.impressions;
        this.consultancyType= c.consultancyType.type;
    }

}
