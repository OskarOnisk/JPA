package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.AddressService;
import com.jpacourse.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController
{

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/{id}") // http://localhost:8091/patient/1
    PatientTO findPatientById(@PathVariable final Long id) {
        final PatientTO patient = patientService.findById(id);
        if(patient != null)
        {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

}
