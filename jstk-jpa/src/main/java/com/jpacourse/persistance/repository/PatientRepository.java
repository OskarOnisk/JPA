package com.jpacourse.persistance.repository;

import com.jpacourse.persistance.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>
{

    @Query("select pat from PatientEntity pat where pat.lastName = :patientLastName")
    List<PatientEntity> findByLastName(@Param("patientLastName") String lastName);

}
