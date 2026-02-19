package com.jpacourse.persistance.repository;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long>, JpaSpecificationExecutor<VisitEntity>
{
@Query("SELECT vis FROM VisitEntity vis WHERE vis.id =:pId")
    List<VisitEntity> findVisitById(@Param("pId") Long Id);


}