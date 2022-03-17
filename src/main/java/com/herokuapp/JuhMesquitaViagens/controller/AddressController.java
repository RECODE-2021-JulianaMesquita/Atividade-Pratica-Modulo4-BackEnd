package com.herokuapp.JuhMesquitaViagens.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herokuapp.JuhMesquitaViagens.exception.ResourceNotFoundException;
import com.herokuapp.JuhMesquitaViagens.model.Address;
import com.herokuapp.JuhMesquitaViagens.repository.AddressRepository;

@CrossOrigin(origins = "https://juhmesquitaviagens-front-end.herokuapp.com")
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    // get all endereços
    @GetMapping("/alladdress")
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    // create endereço rest api
    @PostMapping("/create")
    public Address createAddress(@RequestBody Address createAddress) {
        return addressRepository.save(createAddress);
    }

    // get endereço by id rest api
    @GetMapping("/address/{id}")
    public ResponseEntity <Address> getAddressById(@PathVariable int id) {
        Address addressId = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados o endereÃ§o com o id = " + id));
        return ResponseEntity.ok(addressId);
    }

    // update endereço rest api
    @PutMapping("/address/{id}")
     public ResponseEntity <Address> updatePackage(@PathVariable int id, @RequestBody Address addressDetails) {
        Address addressEdit = addressRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Não existe no banco de dados o endereÃ§o com o id = " + id));
 
        addressEdit.setState(addressDetails.getState());
        addressEdit.setCountry(addressDetails.getCountry());
        addressEdit.setMunicipality(addressDetails.getMunicipality());
        addressEdit.setCode(addressDetails.getCode());
        Address updatedAdress = addressRepository.save(addressEdit);
        return ResponseEntity.ok(updatedAdress);
     }

     // delete endereÃ§o rest api
    @DeleteMapping("/packages/{id}")
    public ResponseEntity<Map<String,Boolean>>deletePackage(@PathVariable int id) {
        Address addressDelete = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe no banco de dados o endereÃ§o com o id = " + id));
        addressRepository.delete(addressDelete);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
