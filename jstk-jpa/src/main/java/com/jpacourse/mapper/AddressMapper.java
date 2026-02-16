package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.persistance.entity.AddressEntity;

import java.util.ArrayList;
import java.util.List;

public final class AddressMapper
{

    public static AddressTO mapToTO(final AddressEntity addressEntity)
    {
        if (addressEntity == null)
        {
            return null;
        }
        final AddressTO addressTO = new AddressTO();
        addressTO.setId(addressEntity.getId());
        addressTO.setAddressLine1(addressEntity.getAddressLine1());
        addressTO.setAddressLine2(addressEntity.getAddressLine2());
        addressTO.setCity(addressEntity.getCity());
        addressTO.setPostalCode(addressEntity.getPostalCode());
        return addressTO;
    }

    public static List<AddressTO> mapToTOs(final List<AddressEntity> addressEntityList)
    {
        List<AddressTO> addressTOList = new ArrayList<AddressTO>();
        addressEntityList.forEach(entity -> addressTOList.add(AddressMapper.mapToTO(entity)));
        return addressTOList;
    }

    public static AddressEntity mapToEntity(final AddressTO addressTO)
    {
        if(addressTO == null)
        {
            return null;
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressTO.getId());
        addressEntity.setAddressLine1(addressTO.getAddressLine1());
        addressEntity.setAddressLine2(addressTO.getAddressLine2());
        addressEntity.setCity(addressTO.getCity());
        addressEntity.setPostalCode(addressTO.getPostalCode());
        return addressEntity;
    }
}
