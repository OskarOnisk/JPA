package com.jpacourse.service.impl;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.DoctorUpdateTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.mapper.DoctorMapper;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.repository.DoctorRepository;
import com.jpacourse.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DoctorServiceImpl implements DoctorService
{
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository pDoctorRepository)
    {
        doctorRepository = pDoctorRepository;
    }

    @Override
    public DoctorTO findById(Long id)
    {
        final DoctorEntity entity = doctorRepository.findById(id).orElse(null);
        return DoctorMapper.mapToTO(entity);
    }

    @Override
    public List<DoctorTO> findByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public DoctorTO create(DoctorTO doctorTO) {
        return null;
    }

    @Override
    public void update(DoctorUpdateTO doctor) {
        if (doctor == null || doctor.getId() == null){
            throw new IllegalArgumentException("DoctorUpdateTo or id is null");
        }

        DoctorEntity entity = doctorRepository.findById(doctor.getId()).orElseThrow(() -> new IllegalArgumentException("DoctorEntity not found, id= "+ doctor.getId()));

        entity.setFirstName(doctor.getFirstName());
        entity.setLastName(doctor.getLastName());
        entity.setEmail(doctor.getEmail());
        entity.setTelephoneNumber(doctor.getTelephoneNumber());
        entity.setDoctorNumber(doctor.getDoctorNumber());
        entity.setSpecialization(doctor.getSpecialization());

        doctorRepository.save(entity);

    }


}
