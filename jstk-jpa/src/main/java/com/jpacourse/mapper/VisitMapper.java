package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class VisitMapper
{

    public static VisitTO mapToTO(final VisitEntity visitEntity)
    {
        if (visitEntity == null)
        {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setVisitTime(visitEntity.getTime());
        if (visitEntity.getDoctor() != null){
            visitTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
            visitTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        }

        return visitTO;
    }

    public static List<VisitTO> mapToTOs(final List<VisitEntity> visitEntityList)
    {
        List<VisitTO> visitTOList = new ArrayList<VisitTO>();
        visitEntityList.forEach(entity -> visitTOList.add(VisitMapper.mapToTO(entity)));
        return visitTOList;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO)
    {
        if(visitTO == null)
        {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
//        visitEntity.setDescription();
        return visitEntity;
    }
}
