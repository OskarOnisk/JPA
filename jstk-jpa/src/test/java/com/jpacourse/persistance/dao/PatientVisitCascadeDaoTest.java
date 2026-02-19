package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.repository.PatientRepository;
import com.jpacourse.persistance.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientVisitCascadeDaoTest
{
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Transactional
    @Test
    public void testShouldCascadeSavePatientAndVisit() {

        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jim");
        patient.setLastName("Smith");
        patient.setTelephoneNumber("1234567890");
        patient.setEmail("jim.smith@example.com");
        patient.setPatientNumber("P002");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));

        VisitEntity visit = new VisitEntity();
        visit.setTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        visit.setDescription("test");
        visit.setPatient(patient);
        patient.setVisits(List.of(visit));


        // when
        PatientEntity savedPatient = patientRepository.save(patient);

        // then
        assertThat(savedPatient.getId()).isNotNull();
        assertThat(savedPatient.getVisits()).hasSize(1);
        VisitEntity savedVisit = savedPatient.getVisits().get(0);
        assertThat(savedVisit.getId()).isNotNull();
        VisitEntity visitFromDb = visitRepository.findById(savedVisit.getId()).orElse(null);
        assertThat(visitFromDb).isNotNull();
        assertThat(visitFromDb.getPatient()).isNotNull();
        assertThat(visitFromDb.getPatient().getId()).isEqualTo(savedPatient.getId());
    }

    @Transactional
    @Test
    public void testShouldCascadeRemoveVisitsWhenPatientDeleted() {
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jim");
        patient.setLastName("Smith");
        patient.setTelephoneNumber("1234567890");
        patient.setEmail("jim.smith@example.com");
        patient.setPatientNumber("P002");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));
        VisitEntity visit = new VisitEntity();
        visit.setTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        visit.setDescription("test");

        visit.setPatient(patient);
        patient.setVisits(List.of(visit));

        PatientEntity savedPatient = patientRepository.save(patient);
        Long visitId = savedPatient.getVisits().get(0).getId();
        Long patientId = savedPatient.getId();

        assertThat(visitRepository.findById(visitId)).isPresent();
        assertThat(patientRepository.findById(patientId)).isPresent();

        //when

        patientRepository.deleteById(patientId);

        //then

        assertThat(visitRepository.findById(visitId)).isEmpty();
        assertThat(patientRepository.findById(patientId)).isEmpty();
    }





}
