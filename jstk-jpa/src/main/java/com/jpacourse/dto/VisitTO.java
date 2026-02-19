package com.jpacourse.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class VisitTO implements Serializable {
    private Long id;
    private LocalDateTime visitTime;
    private String doctorFirstName;
    private String doctorLastName;
    private List<String> treatmentTypes;

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public List<String> getTreatmentTypes() {
        return treatmentTypes;
    }

    public void setTreatmentTypes(List<String> treatmentTypes) {
        this.treatmentTypes = treatmentTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

}
