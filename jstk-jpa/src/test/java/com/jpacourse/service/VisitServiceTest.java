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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class VisitServiceTest
{
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VisitService visitService;

    @Test
    @Transactional
    void testShouldReturnVisitsForExistingPatient(){
        //given

        Long patientId = 1L;

        //when

        List<VisitTO> visits = visitService.findVisitByPatientId(patientId);

        //then

        assertThat(visits).isNotNull();
        assertThat(visits.size()).isEqualTo(3);

    }

    @Test
    @Transactional
    void testShouldReturnVisitsEmptyListWhenNoPatientHasNoVisit(){
        //given

        Long patientId =  3L;

        //when

        List<VisitTO> visits = visitService.findVisitByPatientId(patientId);

        //than

        assertThat(visits).isNotNull();
        assertThat(visits).isEmpty();
    }

    @Test
    @Transactional
    void testShouldThrowWhenPatientIdIsNull(){
        assertThatThrownBy(() -> visitService.findVisitByPatientId(null)).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @Transactional
    void testShouldThrowWhenPatientIdIsNonPositive(){
        assertThatThrownBy(() -> visitService.findVisitByPatientId(0L)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> visitService.findVisitByPatientId(-1L)).isInstanceOf(IllegalArgumentException.class);
    }
}
