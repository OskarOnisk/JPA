package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface VisitService
{
    VisitTO findById(final Long id);

    List<VisitTO> findByDoctorId(final Long doctorId);

    VisitTO create(final VisitTO visitTO);

}
