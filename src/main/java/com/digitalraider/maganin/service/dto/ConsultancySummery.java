package com.digitalraider.maganin.service.dto;


import com.digitalraider.maganin.domain.Consultancy;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Cacheable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@RegisterForReflection
@Cacheable
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
        if(c.doctor != null)
        this.doctor= c.doctor.name;
        this.impressions= c.impressions;
        if(c.consultancyType != null)
        this.consultancyType= c.consultancyType.type;
    }

}
