package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController
{

    private final AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{id}") // http://localhost:8091/address/901
    AddressTO findById(@PathVariable final Long id) {
        final AddressTO address = addressService.findById(id);
        if(address != null)
        {
            return address;
        }
        throw new EntityNotFoundException(id);
    }

    @GetMapping("/address") //http://localhost:8091/address?city=Wro
    List<AddressTO> findByCity(@RequestParam final String city) {
        return addressService.findByCity(city);
    }


    @PostMapping("/address/create")
    public ResponseEntity<AddressTO> createAddress(@RequestBody AddressTO addressTO) {
        AddressTO created = addressService.create(addressTO);
        return ResponseEntity.ok(created);
    }

}
