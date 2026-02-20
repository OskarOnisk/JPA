package com.jpacourse.service.impl;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.repository.PatientRepository;
import com.jpacourse.persistance.repository.VisitRepository;
import com.jpacourse.service.DoctorService;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository pVisitRepository) {
        visitRepository = pVisitRepository;
    }

    @Override
    public VisitTO findById(Long id) {
        final VisitEntity entity = visitRepository.findById(id).orElse(null);
        return VisitMapper.mapToTO(entity);
    }

    @Override
    public List<VisitTO> findByDoctorId(Long doctorId) {
        return List.of();
    }

//    @Override
//    public List<VisitTO> findByDoctorId(Long doctor)
//    {
//        final List<VisitEntity> entityList = visitRepository.findPatientByDoctorId(doctor);
//        return VisitMapper.mapToTOs(entityList);
//    }

    @Override
    public VisitTO create(VisitTO visitTO) {
        final VisitEntity entity = VisitMapper.mapToEntity(visitTO);
        return VisitMapper.mapToTO(visitRepository.save(entity));
    }

    @Override
    public List<VisitTO> findVisitByPatientId(Long patientId) {
        if (patientId == null) {
            throw new IllegalArgumentException("patientId cannot be null");
        }
        if (patientId <= 0){
            throw new IllegalArgumentException("patientId must be greater than 0");
        }
        return visitRepository.findVisitByPatientId(patientId).stream().map(VisitMapper::mapToTO).toList();
    }


}
