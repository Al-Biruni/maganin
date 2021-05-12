package com.digitalraider.maganin.service.dto;

import com.digitalraider.maganin.domain.Consultancy;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Cacheable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ConsultancyDTO {

    public Long id;
    public Integer userId;
    public String name;
    public LocalDateTime dateAdded;
    public String age;
    public String gender;
    public String religion;
    public String rel2;
    public String edu;
    public String social;
    public String econ;
    public String hop;
    public String job;
    public String country;
    public String origin;
    public String email;
    public String title;
    public String question;
    public String answer;
    public Long consultancyType;
    public Integer doctor;
    public boolean show;

    public ConsultancyDTO(Consultancy c) {
        this.id = c.id;
        this.userId = c.userId;
        this.name = c.name;
        this.dateAdded =  LocalDateTime.ofInstant(c.date, ZoneOffset.UTC);
        this.age = c.age;
        this.gender = c.gender;
        this.religion = c.religion;
        this.rel2 = c.rel2;
        this.edu = c.edu;
        this.social = c.social;
        this.econ = c.econ;
        this.hop = c.hop;
        this.job = c.job;
        this.country = c.country;
        this.origin = c.origin;
        this.email = c.email;
        this.title = c.title;
        this.question = c.question;
        this.answer = c.answer;
        this.consultancyType = c.consultancyType.id;
        this.doctor = c.doctor.id;
        this.show = c.show;
    }


    public ConsultancyDTO() {
    }
}
