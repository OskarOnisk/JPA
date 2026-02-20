package com.jpacourse.persistance.repository;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>, JpaSpecificationExecutor<PatientEntity>
{

    @Query("SELECT pat FROM PatientEntity pat where pat.id = :pId ")
    PatientEntity findPatientById(@Param("pId") Long id);

    @Query("SELECT p FROM PatientEntity p WHERE LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastNamePart, '%'))")
    List<PatientEntity> findByLastNameIgnoreCase(@Param("lastNamePart") String lastNamePart);

    @Query("SELECT p FROM PatientEntity p LEFT JOIN p.visits v GROUP BY p HAVING COUNT(v) > :minVisits")
    List<PatientEntity> findVisitsByMinVisits(@Param("minVisits") Long minVisits);

}