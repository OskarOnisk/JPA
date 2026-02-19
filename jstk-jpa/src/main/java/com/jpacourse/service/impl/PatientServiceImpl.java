package com.jpacourse.service.impl;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.repository.PatientRepository;
import com.jpacourse.service.DoctorService;
import com.jpacourse.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository pPatientRepository)
    {
        patientRepository = pPatientRepository;
    }

    @Override
    public PatientTO findById(Long id)
    {
        final PatientEntity entity = patientRepository.findById(id).orElse(null);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        PatientEntity entity = patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient not found with id " + id));

    patientRepository.delete(entity);}

//    @Override
//    public List<PatientTO> findByFirstName(String firstName)
//    {
//        final List<PatientEntity> entityList = patientRepository.findPatientByFirstName(firstName);
//        return PatientMapper.mapToTOs(entityList);
//    }

//    @Override
//    public PatientTO create(PatientTO patientTO) {
//        final PatientEntity entity = PatientMapper.mapToEntity(patientTO);
//        return PatientMapper.mapToTO(patientRepository.save(entity));
//    }


}
