package com.jpacourse.persistance.specification;

import com.jpacourse.persistance.entity.AddressEntity;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecification
{

    public static Specification<AddressEntity> byAddressLine1(final String addressLine1) {
        return (root, query, cb) ->
                cb.equal(root.get("addressLine1"), addressLine1);
    }

    public static Specification<AddressEntity> byCity(final String city) {
        return (root, query, cb) ->
                cb.equal(root.get("city"), city);
    }

}
