package az.bcc.dogapplicationspringbootrelations.controller;

import az.bcc.dogapplicationspringbootrelations.model.Person;
import az.bcc.dogapplicationspringbootrelations.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/addPerson")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body("Elave edildi");
    }

    @GetMapping("/getAllPersons")
    public ResponseEntity<ArrayList<Person>> getAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted");
    }

}
