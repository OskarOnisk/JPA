package com.jpacourse.persistance.dao;

import com.jpacourse.dto.VisitTO;

import java.time.LocalDateTime;

public interface PatientDao {
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description);
}
