package com.jpacourse.service.impl;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.repository.AddressRepository;
import com.jpacourse.persistance.specification.AddressSpecification;
import com.jpacourse.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AddressServiceImpl implements AddressService
{
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository pAddressRepository)
    {
        addressRepository = pAddressRepository;
    }

    @Override
    public AddressTO findById(Long id)
    {
        final AddressEntity entity = addressRepository.findById(id).orElse(null);
        return AddressMapper.mapToTO(entity);
    }

    @Override
    public List<AddressTO> findByCity(String city)
    {
        final List<AddressEntity> entityList = addressRepository.findAddressByCity(city);
        return AddressMapper.mapToTOs(entityList);
    }

    @Override
    public AddressTO create(AddressTO addressTO) {
        final AddressEntity entity = AddressMapper.mapToEntity(addressTO);
        return AddressMapper.mapToTO(addressRepository.save(entity));
    }

    @Override
    public List<AddressTO> findByParams(AddressTO addressTO) {
        Specification<AddressEntity> spec = Specification.allOf();

        if(addressTO.getAddressLine1() != null )
        {
            spec = spec.and(AddressSpecification.byAddressLine1(addressTO.getAddressLine1()));
        }

        if(addressTO.getCity() != null )
        {
            spec = spec.and(AddressSpecification.byCity(addressTO.getCity()));
        }

        return AddressMapper.mapToTOs(addressRepository.findAll(spec));
    }


}
