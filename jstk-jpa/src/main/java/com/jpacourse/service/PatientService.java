package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;

import java.util.List;

public interface PatientService
{
    PatientTO findById(final Long id);

    void deleteById(final Long id);

//    List<PatientTO> findByFirstName(final String firstName);
//
//    PatientTO create(final PatientTO patientTO);

}
