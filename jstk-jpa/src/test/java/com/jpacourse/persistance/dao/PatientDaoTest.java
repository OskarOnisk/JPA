package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.repository.AddressRepository;
import com.jpacourse.persistance.repository.DoctorRepository;
import com.jpacourse.persistance.repository.PatientRepository;
import com.jpacourse.persistance.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VisitRepository visitRepository;

    @Transactional
    @Test
    void testShouldAddVisitToPatientUsingCascadeMerge() {
        //given

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Doctor");
        doctor.setLastName("Doctor");
        doctor.setDoctorNumber("0001");
        DoctorEntity savedDoctor = doctorRepository.save(doctor);

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Patient");
        patient.setLastName("Patient");
        patient.setPatientNumber("0002");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));
        PatientEntity savedPatient = patientRepository.save(patient);

        Long patientId = savedPatient.getId();
        Long doctorId = savedDoctor.getId();

        LocalDateTime visitTime = LocalDateTime.now();
        String description = "DAO - dodanie wizyty merge";

        // when

        patientDao.addVisitToPatient(patientId, doctorId, visitTime, description);

        // then

        PatientEntity patientFromDB = patientRepository.findById(patientId).orElse(null);
        assertThat(patientFromDB.getVisits()).isNotNull();
        assertThat(patientFromDB.getVisits()).hasSize(1);

        VisitEntity visitFromPatient = patientFromDB.getVisits().get(0);
        assertThat(visitFromPatient.getId()).isNotNull();
        assertThat(visitFromPatient.getTime()).isEqualTo(visitTime);
        assertThat(visitFromPatient.getDescription()).isEqualTo(description);

        VisitEntity visitFromDb = visitRepository.findById(visitFromPatient.getId()).orElseThrow();
        assertThat(visitFromDb.getPatient()).isNotNull();
        assertThat(visitFromDb.getPatient().getId()).isEqualTo(patientId);

        assertThat(doctorRepository.findById(doctorId)).isPresent();

        List<VisitEntity> allVisits = visitRepository.findAll();
        assertThat(allVisits.stream().anyMatch(v -> description.equals(v.getDescription()) &&
                visitTime.equals(v.getTime()) && v.getPatient() != null && patientId.equals(v.getPatient().getId())
        && v.getDoctor() != null && doctorId.equals(v.getDoctor().getId()))).isTrue();
    }
}