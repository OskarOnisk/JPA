package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;

import java.util.List;

public interface AddressService
{
    AddressTO findById(final Long id);

    List<AddressTO> findByCity(final String city);

    AddressTO create(final AddressTO addressTO);

    List<AddressTO> findByParams(final AddressTO addressTO);
}
