package com.jpacourse.persistance.repository;

import com.jpacourse.persistance.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>, JpaSpecificationExecutor<AddressEntity>
{

    @Query("SELECT adr FROM AddressEntity adr where adr.city = :pCity ")
    List<AddressEntity> findAddressByCity(@Param("pCity") String city);

}