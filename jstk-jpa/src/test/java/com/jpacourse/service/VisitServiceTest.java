package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
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
public class PatientServiceTest
{
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    @Transactional
    void testShouldDeletePatientAndCascadeDeleteVisitsButNotDoctors()
    {
        //given

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Adam");
        doctor.setLastName("Nowak");
        doctor.setDoctorNumber("Doctor1");
        DoctorEntity savedDoctor = doctorRepository.save(doctor);

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Adam");
        patient.setLastName("Nowak");
        patient.setPatientNumber("Patient1");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));

        VisitEntity visit = new VisitEntity();

        visit.setPatient(patient);
        visit.setDoctor(savedDoctor);
        visit.setTime(LocalDateTime.now());
        visit.setDescription("Do usuniecia przez servis");
        patient.setVisits(List.of(visit));

        PatientEntity savedPatient = patientRepository.save(patient);

        Long patientId = savedPatient.getId();
        Long visitId = savedPatient.getVisits().get(0).getId();
        Long doctorId = savedDoctor.getId();

        assertThat(patientRepository.findById(patientId)).isPresent();
        assertThat(visitRepository.findById(visitId)).isPresent();
        assertThat(doctorRepository.findById(doctorId)).isPresent();

        //when

        patientService.deleteById(patientId);

        //then

        assertThat(visitRepository.findById(visitId)).isEmpty();
        assertThat(doctorRepository.findById(doctorId)).isPresent();
        assertThat(patientRepository.findById(patientId)).isEmpty();

    }

    @Test
    @Transactional
    void testShouldReturnPatientTOByIdWithVisitsStructure()
    {
        //given

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Adam");
        doctor.setLastName("Nowak");
        doctor.setDoctorNumber("Doctor1");
        DoctorEntity savedDoctor = doctorRepository.save(doctor);
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Adam");
        patient.setLastName("Nowak");
        patient.setPatientNumber("Patient1");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));
        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(savedDoctor);
        visit.setTime(LocalDateTime.now().plusDays(1));
        patient.setVisits(List.of(visit));
        PatientEntity savedPatient = patientRepository.save(patient);
        Long patientId = savedPatient.getId();

        //when

        PatientTO result = patientService.findById(patientId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("Adam");
        assertThat(result.getLastName()).isEqualTo("Nowak");
        assertThat(result.getVisits()).isNotNull();
        assertThat(result.getVisits()).hasSize(1);
        VisitTO visitTO = result.getVisits().get(0);
        assertThat(visitTO.getVisitTime()).isEqualTo(visit.getTime());
        assertThat(visitTO.getDoctorFirstName()).isEqualTo("Adam");
        assertThat(visitTO.getDoctorLastName()).isEqualTo("Nowak");
    }



}
