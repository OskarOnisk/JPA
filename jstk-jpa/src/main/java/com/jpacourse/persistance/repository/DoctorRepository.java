package com.jpacourse.persistance.repository;

import com.jpacourse.persistance.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long>, JpaSpecificationExecutor<DoctorEntity>
{

    @Query("SELECT doc FROM DoctorEntity doc where doc.id = :pId ")
    DoctorEntity findDoctorById(@Param("pId") Long id);

}