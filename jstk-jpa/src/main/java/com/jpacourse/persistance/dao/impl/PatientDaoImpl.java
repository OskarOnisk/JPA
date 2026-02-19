package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class PatientDaoImpl implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new EntityNotFoundException("Patient with id " + patientId + " not found");
        }

        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new EntityNotFoundException("Doctor with id " + doctorId + " not found");
        }

        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTime);
        visit.setDescription(description);

        visit.setPatient(patient);
        visit.setDoctor(doctor);

        if (patient.getVisits() == null){
            patient.setVisits(new ArrayList<>());
        }
        patient.getVisits().add(visit);

        entityManager.merge(patient);
    }
}
