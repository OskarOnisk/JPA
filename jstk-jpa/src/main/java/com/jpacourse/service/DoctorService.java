package com.jpacourse.service;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.DoctorUpdateTO;

import java.util.List;

public interface DoctorService
{
    DoctorTO findById(final Long id);

    List<DoctorTO> findByFirstName(final String firstName);

    DoctorTO create(final DoctorTO doctorTO);

    void update(DoctorUpdateTO doctor);

}
